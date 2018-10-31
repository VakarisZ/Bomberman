/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Client.ClientBoard;
import java.awt.Graphics2D;
import java.awt.Image;


/**
 * /**
 *
 * @author mati
 */
public class BombermanSprite {

    private int height, width, position_x, position_y;
    private Image image;
    private boolean moving = false;
    private int movement_speed;
    private int move_x;
    private int move_y;
    private Graphics2D g2d;
    private ClientBoard board;
    private String clientName;

    public BombermanSprite(int position_x, int position_y, int height, int width,
            Image image, ClientBoard board, String clientString) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.image = image;
        this.board = board;
        this.clientName = clientString;
    }
    
    public BombermanSprite(int position_x, int position_y, int height, int width,
            Image image) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.image = image;
    }

    public void ChangeImage(Image image) {
        this.image = image;
    }

    public void Move(int dx, int dy, int movement_speed) {
        this.move_x = dx;
        this.move_y = dy;
        this.moving = true;
        this.movement_speed = movement_speed;
        
    }
    
    public void Move(int x, int y){
        this.position_x = x;
        this.position_y = y;
        this.moving = false;
    }
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
    
    public int getX(){
        return this.position_x;
    }
    
    public int getY(){
        return this.position_y;
    }
   
    public boolean equals(BombermanSprite otherEntry)
    {
        return clientName.equals(otherEntry.clientName);
    }
    
    public boolean equals(String otherEntry)
    {
        return clientName.equals(otherEntry);
    }
}
