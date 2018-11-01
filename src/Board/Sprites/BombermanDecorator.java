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
public class BombermanDecorator implements BombermanSpriteInterface{
    protected BombermanSpriteInterface bps;
    
    public BombermanDecorator(BombermanSpriteInterface bs) {
        this.bps = bs;
    }
    @Override 
    public void Assemble(){
        this.bps.Assemble();
        
    }

    @Override
    public void ChangeImage(Image image) {
        this.bps.ChangeImage(image);
    }

    @Override
    public void Move(int dx, int dy, int movement_speed) {
        this.bps.Move(dx, dy, movement_speed);
    }

    @Override
    public void Move(int x, int y) {
        this.bps.Move(x, y);
    }

    @Override
    public boolean Move(String name, int x, int y) {
        return this.bps.Move(name, x, y);
    }

    @Override
    public void moveAs() {
        this.bps.moveAs();
    }

    @Override
    public void addMove(Movement movement) {
        this.bps.addMove(movement);
    }

    @Override
    public void Tick(Graphics2D g2d) {
        this.bps.Tick(g2d);
    }

    @Override
    public int getX() {
        return this.bps.getX();
        
    }

    @Override
    public int getY() {
       return this.bps.getY();
    }

    @Override
    public boolean equals(BombermanSpriteInterface otherEntry) {
        return this.bps.equals(otherEntry);
    }

    @Override
    public boolean equals(String otherEntry) {
        return this.bps.equals(otherEntry);
    }

    @Override
    public String getName() {
        return this.bps.getName();
        
    }
}
