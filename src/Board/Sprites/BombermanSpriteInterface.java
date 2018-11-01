/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Sprites;

import Movement.Movement;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author mati
 */
public interface BombermanSpriteInterface {
    public void Assemble();
    public void ChangeImage(Image image);    
    public void Move(int dx, int dy, int movement_speed);
    public void Move(int x, int y);
    public boolean Move(String name, int x, int y);
    public void moveAs();
    public void addMove(Movement movement);
    public void Tick(Graphics2D g2d);
    public int getX();
    public int getY();
    public boolean equals(BombermanSpriteInterface otherEntry);
    public boolean equals(String otherEntry);
    public String getName();
}
