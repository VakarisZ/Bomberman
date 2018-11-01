/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Sprites;

import Client.ClientBoard;
import Movement.Movement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;


/**
 * /**
 *
 * @author mati
 */
public class BombermanSprite implements BombermanSpriteInterface {

    private int height, width, position_x, position_y;
    private Image image;
    private boolean moving = false;
    private int movement_speed;
    private int move_x;
    private int move_y;
    private Graphics2D g2d;
    private ClientBoard board;
    private String clientName;
    public ArrayList<Movement> moveList =  new ArrayList<Movement>();

    public BombermanSprite(BombermanSprite b){
        this.height = b.height;
        this.width = b.width;
        this.position_x = b.position_x;
        this.position_y = b.position_y;
        this.image = b.image;
        this.board = b.board;
        this.clientName = b.clientName;
    }

    public BombermanSprite(int position_x, int position_y, int height, int width,
            ClientBoard board, String clientString) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.board = board;
        this.clientName = clientString;
    }
    public BombermanSprite(int position_x, int position_y, int height, int width) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.board = board;
    }
    
    @Override
    public void Assemble(){
        this.image = new ImageIcon("images/bomber_fixed.png").getImage();
    }
    @Override
    public void ChangeImage(Image image) {
        this.image = image;
    }
    @Override
    public void Move(int dx, int dy, int movement_speed) {
        this.move_x = dx;
        this.move_y = dy;
        this.moving = true;
        this.movement_speed = movement_speed;
        
    }
    @Override
    public void Move(int x, int y){
        this.position_x = x;
        this.position_y = y;
        this.moving = false;
    }
    @Override
    public boolean Move(String name, int x, int y){
        if (this.clientName.equals(name)){
            this.position_x = x;
            this.position_y = y;
            this.moving = false;
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void moveAs(){
        for(Movement a : moveList) {
            a.move();
        }
        moveList =  new ArrayList<Movement>();
    }
    @Override
    public void addMove(Movement movement){
        this.moveList.add(movement);
    }
    @Override
    public void Tick(Graphics2D g2d) {
        this.g2d = g2d;
        if (this.moving) {
            this.position_x += move_x * this.movement_speed;
            this.position_y += move_y * this.movement_speed;
            this.moving = false;
        }
        g2d.drawImage(image, position_x - width/2, 
               position_y - height/2, width, height, board);
    }
    @Override
    public int getX(){
        return this.position_x;
    }
    
    @Override
    public int getY(){
        return this.position_y;
    }
    @Override
    public boolean equals(BombermanSpriteInterface otherEntry)
    {
        return clientName.equals(otherEntry.getName());
    }
    @Override
    public boolean equals(String otherEntry)
    {
        return clientName.equals(otherEntry);
    }

    @Override
    public String getName() {
        return clientName;
    }
}
