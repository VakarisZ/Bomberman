/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
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

/**
 * /**
 *
 * @author mati
 */
public class CustomSprite {

    private int height, width, position_x, position_y;
    private Image image;
    private boolean moving = false;
    private int movement_speed;
    private int move_x;
    private int move_y;
    private Graphics2D g2d;
    private Board board;

    CustomSprite(int position_x, int position_y, int height, int width,
            Image image, Board board) {
        this.height = height;
        this.width = width;
        this.position_x = position_x;
        this.position_y = position_y;
        this.image = image;
        this.board = board;
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
}
