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
    private int animation_steps;
    private int animation_speed;
    private int current_animation_step;
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

    public void Move(int dx, int dy, int animation_steps) {
        if (this.moving){
            this.position_x += move_x * (animation_steps - current_animation_step) / animation_steps;
            this.position_y += move_y * (animation_steps - current_animation_step) / animation_steps;
        }
        this.move_x = dx;
        this.move_y = dy;
        this.moving = true;
        this.animation_steps = animation_steps;
        this.current_animation_step = 0;
        
    }

    public void Tick(Graphics2D g2d) {
        this.g2d = g2d;
        if (this.moving) {
            if (move_x != 0) {
                this.position_x += move_x / animation_steps;
                current_animation_step++;
                if (current_animation_step >= animation_steps){
                    this.moving = false;
                }
            }
            else{
                
                this.position_y += move_y / animation_steps;
                current_animation_step++;
                if (current_animation_step >= animation_steps){
                    this.moving = false;
                }
            
            }
        }
        g2d.drawImage(image, position_x - width/2, 
               position_y - height/2, width, height, board);
    }
}
