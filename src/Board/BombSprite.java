/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Client.ClientBoard;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author mati
 */
public class BombSprite {
     private int height, width, position_x, position_y;
    private Image image;
    private int move_x;
    private int move_y;
    private Graphics2D g2d;
    private ClientBoard board;

    public BombSprite(int position_x, int position_y, int height, int width, ClientBoard board) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.image = new ImageIcon("images/bomber_fixed.png").getImage();
        this.board = board;
    }
    
    public BombSprite(int position_x, int position_y, int height, int width,
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

    
    public void Move(int x, int y){
        this.position_x = x;
        this.position_y = y;
    }
    public boolean Move(String name, int x, int y){
       
            this.position_x = x;
            this.position_y = y;
            return true;
    }
    
    

    public void DoTick(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.drawImage(image, position_x - width/2, 
               position_y - height/2, width, height, board);
    }
    
    public int getX(){
        return this.position_x;
    }
    
    public int getY(){
        return this.position_y;
    }
    
}
