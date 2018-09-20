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
        
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.lang.Math;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import Board.Cell;

public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;

    private boolean inGame = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 32;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int PAC_ANIM_DELAY = 2;
    private final int MAX_GHOSTS = 12;
    private final int BOMBERMAN_SPEED = 6;

    private int pacAnimCount = PAC_ANIM_DELAY;
    private int pacAnimDir = 1;
    private int bombermanAnimPos = 0;
    private int N_GHOSTS = 1;
    private int pacsLeft, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image ghost;
    private Image bomber;
    private Image bomberman1, bomberman2up, bomberman2left, bomberman2right, bomberman2down;
    private Image bomberman3up, bomberman3down, bomberman3left, bomberman3right;
    private Image bomberman4up, bomberman4down, bomberman4left, bomberman4right;

    private int bomberman_x, bomberman_y;
    private int bombermand_x = 32; // Dimensions of the bomberman
    private int bombermand_y = 32;
    private int req_dx, req_dy, view_dx, view_dy;

    private final short levelData[] = {
        19, 0, 26, 0, 18, 0, 18, 0, 18, 0, 18, 0, 18, 0, 22,
        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
        17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
        25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
        1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
        1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
        1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
    };
    
    private final Cell[][] mapCells = Cell.getMapCells(N_BLOCKS, N_BLOCKS, BLOCK_SIZE);
    
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    public Board() {

        loadImages();
        initVariables();
        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);        
    }

    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(600, 600);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        timer = new Timer(40, this);
        timer.start();
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

    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {

            moveBomberman();
            drawBomberman(g2d);
            moveGhosts(g2d);
            checkMaze();
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

    private void moveGhosts(Graphics2D g2d) {

        short i;
        int pos;
        int count;

        for (i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            if (bomberman_x > (ghost_x[i] - 12) && bomberman_x < (ghost_x[i] + 12)
                    && bomberman_y > (ghost_y[i] - 12) && bomberman_y < (ghost_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {

        g2d.drawImage(ghost, x, y, this);
    }

    private void drawBomber(Graphics2D g2d, int x, int y) {

        g2d.drawImage(bomber, x, y, this);
    }
    
    private void moveBomberman() {
        // If bomber wants to walk
        if (req_dx != 0 || req_dy != 0) {
            // We find the cell we are in
            int cell_x = (int)Math.floor((double)bomberman_x / BLOCK_SIZE);
            int cell_y = (int)Math.floor((double)bomberman_y / BLOCK_SIZE);
            Map<String, Boolean> current = mapCells[cell_y][cell_x].getBorders();
            // We find out destination 
            int d_x = (bomberman_x + BOMBERMAN_SPEED * req_dx);
            int d_y = (bomberman_y + BOMBERMAN_SPEED * req_dy);
            int d_cell_x = (int)Math.floor((((double)d_x) / BLOCK_SIZE));
            int d_cell_y = (int)Math.floor((((double)d_y) / BLOCK_SIZE));
            // If try to go out of bounds
            if(d_cell_x < 0 || d_cell_x == N_BLOCKS || d_cell_y < 0 || d_cell_y == N_BLOCKS){
                return;
            }
            Map<String, Boolean> destination = mapCells[d_cell_y][d_cell_x].getBorders();
            // If destination cell is not the same as origin's
            if(cell_x != d_cell_x || cell_y != d_cell_y){
                // Going right
                if(req_dx == 1){
                    if(current.get("right_b") || destination.get("left_b")){
                        bomberman_x = mapCells[cell_y][cell_x].getX() + BLOCK_SIZE - 1;
                    } else {
                        bomberman_x = d_x;
                    }
                }
                // Going left
                if(req_dx == -1){
                    if(current.get("left_b") || destination.get("right_b")){
                        bomberman_x = mapCells[cell_y][cell_x].getX() + 1;
                    } else {
                        bomberman_x = d_x;
                    }
                }
                // Going up
                if(req_dy == -1){
                    if(current.get("top_b") || destination.get("bottom_b")){
                        bomberman_y = mapCells[cell_y][cell_x].getY() + 1;
                    } else {
                        bomberman_y = d_y;
                    }
                }
                // Going down
                if(req_dy == 1){
                    if(current.get("bottom_b") || destination.get("top_b")){
                        bomberman_y = mapCells[cell_y][cell_x].getY() + BLOCK_SIZE - 1;
                    } else {
                        bomberman_y = d_y;
                    }
                }
            } else {
                bomberman_x = d_x;
                bomberman_y = d_y;
            }
            view_dx = req_dx;
            view_dy = req_dy;
        }
    }

    private void drawBomberman(Graphics2D g2d) {
        g2d.drawImage(bomberman1, bomberman_x - bombermand_x/2, 
                bomberman_y - bombermand_y/2, bombermand_x, bombermand_y, this );
    }
    
    private void drawMaze(Graphics2D g2d) {

        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));
                int i = y / BLOCK_SIZE;
                int j = x / BLOCK_SIZE;
                Cell cell = mapCells[i][j];
                if ((cell.getBorders().get("top_b"))) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE -1, y);
                }

                if ((cell.getBorders().get("right_b"))) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, 
                                 x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
                }

                if ((cell.getBorders().get("bottom_b"))) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((cell.getBorders().get("left_b"))) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
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
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {

        short i;
        int dx = 1;
        int random;

        for (i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = N_BLOCKS * BLOCK_SIZE -1;
            ghost_x[i] = N_BLOCKS * BLOCK_SIZE -1;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        bomberman_x = 15;
        bomberman_y = 15;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;
    }

    private void loadImages() {

        ghost = new ImageIcon("images/ghost.png").getImage();
        bomberman1 = new ImageIcon("images/bomber_fixed.png").getImage();
        bomberman2up = new ImageIcon("images/up1.png").getImage();
        bomberman3up = new ImageIcon("images/up2.png").getImage();
        bomberman4up = new ImageIcon("images/up3.png").getImage();
        bomberman2down = new ImageIcon("images/down1.png").getImage();
        bomberman3down = new ImageIcon("images/down2.png").getImage();
        bomberman4down = new ImageIcon("images/down3.png").getImage();
        bomberman2left = new ImageIcon("images/left1.png").getImage();
        bomberman3left = new ImageIcon("images/left2.png").getImage();
        bomberman4left = new ImageIcon("images/left3.png").getImage();
        bomberman2right = new ImageIcon("images/right1.png").getImage();
        bomberman3right = new ImageIcon("images/right2.png").getImage();
        bomberman4right = new ImageIcon("images/right3.png").getImage();

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

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                    System.out.println("Key pressed"+e);
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                    System.out.println("Key pressed"+e);
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                    System.out.println("Key pressed"+e);
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                    System.out.println("Key pressed"+e);
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    inGame = true;
                    initGame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT
                    || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                req_dx = 0;
                req_dy = 0;
                System.out.println("Worked");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}

