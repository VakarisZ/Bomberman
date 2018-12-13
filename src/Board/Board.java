/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vakaris
 */
package Board;

import Board.Map;
import Board.Sprites.BombermanSprite;
import Board.Sprites.CustomSprite;
import Board.Sprites.BombermanSpriteToCustomSpriteAdapter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.lang.Math;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import Board.Cell;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Movement.*;
import Enemies.*;
import java.util.Iterator;

public class Board extends JPanel{

    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;

    private Map map;
    private int SCREEN_SIZE;
    
    private boolean inGame = false;
    private boolean dying = false;

    private final int MAX_GHOSTS = 12;
    private final int BOMBERMAN_SPEED = 3;
    // Determines the dimensions of bomberman collision detection 
    private final int BOMBERMAN_SIZE = 10;

    private int N_GHOSTS = 1;
    private int pacsLeft, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
    private LinkedList<CustomSprite> sprites = new LinkedList<CustomSprite>();

    private Image ghost;
    private Image bomber;
    private Image bomberman1;
    private Image bomberman3up, bomberman3down, bomberman3left, bomberman3right;
    private Image bomberman4up, bomberman4down, bomberman4left, bomberman4right;

    public int bomberman_x, bomberman_y;
    private int bombermand_x = 32; // Dimensions of the bomberman
    private int bombermand_y = 32;
    public int req_dx, req_dy, view_dx, view_dy;
    private CustomSprite bombie;
    private Socket server_socket;
    private DataInputStream server_in;

    private final int validSpeeds[] = {32};
    private final int maxSpeed = 32;
    public int currentSpeed = 32;
    private short[] screenData;
    private Timer timer;
    
    private GhostContainer ghosts;
    private BotContainer bots;

    public Board() {

        loadImages();
        initVariables();
        initBoard();
    }

    private void initConnection() throws IOException {
        server_socket = null;
        try {
            server_socket = new Socket("localhost", 4000);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        server_in = new DataInputStream(server_socket.getInputStream());

    }

    private void initBoard() {

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);
//        try {
//            initConnection();
//        } catch (IOException ex) {
//            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void addBomb(){
        
    }
    
    public void initVariables() {
        map = Map.getInstance();
        SCREEN_SIZE = map.N_BLOCKS * map.BLOCK_SIZE;
        screenData = new short[map.N_BLOCKS * map.N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(600, 600);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        bomberman_x = map.BLOCK_SIZE / 2;
        bomberman_y = map.BLOCK_SIZE / 2;
        BombermanSprite bombieTemp =  new BombermanSprite(bomberman_x, bomberman_y, bombermand_x, bombermand_y);
        bombie = new BombermanSpriteToCustomSpriteAdapter(bombieTemp);
        sprites.add(bombie);
        
        ghosts = new GhostContainer(1);
        bots = new BotContainer();
        
        ghosts.AddGhost(0, 0);
        bots.addBot(10, 10, 5);
        bots.addBot(30, 30, 5);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    private void doAnim() {

//        pacAnimCount--;
//
//        if (pacAnimCount <= 0) {
//            pacAnimCount = PAC_ANIM_DELAY;
//            bombermanAnimPos = bombermanAnimPos + pacAnimDir;
//
//            if (bombermanAnimPos == (BOMBERMAN_ANIM_COUNT - 1) || bombermanAnimPos == 0) {
//                pacAnimDir = -pacAnimDir;
//            }
//        }
    }

    public void playGame(Graphics2D g2d) {
//        byte message = 4;
//        try {
//            message = server_in.readByte();
//        } catch (IOException ex) {
//            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        switch (message) {
//            case 0:
//                if (dying) {
//
//                    death();
//
//                } else {
                    // Create new movement for bomberman
                    bombie.Move(bomberman_x, bomberman_y);
                    if(req_dx == 1){
                        bombie.addMovement(new BombermanMovementRight(BOMBERMAN_SPEED, bombie, map, BOMBERMAN_SIZE));
                    } else if(req_dx == -1){
                        bombie.addMovement(new BombermanMovementLeft(BOMBERMAN_SPEED, bombie, map, BOMBERMAN_SIZE));
                    } else if(req_dy == 1){
                        bombie.addMovement(new BombermanMovementDown(BOMBERMAN_SPEED, bombie, map, BOMBERMAN_SIZE));
                    } else if(req_dy == -1){
                        bombie.addMovement(new BombermanMovementUp(BOMBERMAN_SPEED, bombie, map, BOMBERMAN_SIZE));
                    }
                    bombie.moveAs();
                    bomberman_x = bombie.getX();
                    bomberman_y = bombie.getY();
                    
                    
                    for (CustomSprite s : sprites) {
//                        s.Tick(g2d);
                    }
//                    checkMaze();

//                }
//        }
    }

    public void moveEnemies(){
        Iterator ghostIter = ghosts.createIterator();
        Iterator botIter = bots.createIterator();
        moveEnemies(ghostIter);
        moveEnemies(botIter);
    }
    
    private void moveEnemies(Iterator iterator){
        while(iterator.hasNext()) {
            Enemy enemy = (Enemy) iterator.next();
            enemy.move();
        }
    }
    
    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    private void drawScore(Graphics2D g) {

        int i;
        String s;

        g.setFont(smallFont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (i = 0; i < pacsLeft; i++) {
            g.drawImage(bomberman3left, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void checkMaze() {
        //initlevel()
    }

    private void death() {

        pacsLeft--;

        if (pacsLeft == 0) {
            inGame = false;
        }

        continueLevel();
    }

    private void drawMaze(Graphics2D g2d) {

        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += map.BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += map.BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));
                int i = y / map.BLOCK_SIZE;
                int j = x / map.BLOCK_SIZE;
                Cell cell = map.getMapCell(i, j);
                if ((cell.getBorders().get("top_b"))) {
                    g2d.drawLine(x, y, x + map.BLOCK_SIZE - 1, y);
                }

                if ((cell.getBorders().get("right_b"))) {
                    g2d.drawLine(x + map.BLOCK_SIZE - 1, y,
                            x + map.BLOCK_SIZE - 1, y + map.BLOCK_SIZE - 1);
                }

                if ((cell.getBorders().get("bottom_b"))) {
                    g2d.drawLine(x, y + map.BLOCK_SIZE - 1, x + map.BLOCK_SIZE - 1,
                            y + map.BLOCK_SIZE - 1);
                }

                if ((cell.getBorders().get("left_b"))) {
                    g2d.drawLine(x, y, x, y + map.BLOCK_SIZE - 1);
                }

            }
        }
    }

    private void initGame() {

        pacsLeft = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 1;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        continueLevel();
    }

    private void continueLevel() {

        short i;
        int dx = 1;
        int random;

        for (i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = map.N_BLOCKS * map.BLOCK_SIZE - 1;
            ghost_x[i] = map.N_BLOCKS * map.BLOCK_SIZE - 1;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = 0; //validSpeeds[random];
        }

        bomberman_x = map.BLOCK_SIZE / 2;
        bomberman_y = map.BLOCK_SIZE / 2;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;
    }

    private void loadImages() {

        ghost = new ImageIcon("images/ghost.png").getImage();
        bomberman1 = new ImageIcon("images/bomber_fixed.png").getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        doAnim();

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
}
