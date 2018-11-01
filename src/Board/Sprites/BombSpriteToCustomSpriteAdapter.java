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
import javax.swing.ImageIcon;

/**
 *
 * @author mati
 */
public class BombSpriteToCustomSpriteAdapter implements CustomSprite{
    private BombSprite bombSprite;
    
    public BombSpriteToCustomSpriteAdapter (BombSprite bs){
        bombSprite = bs;
    }

    @Override
    public void addMovement(Movement movement){
        
    }
    
    @Override
    public void ChangeImage(Image image) {
        bombSprite.ChangeImage(image);
    }

    @Override
    public void Move(int x, int y) {
        bombSprite.Move(x, y);
    }

    @Override
    public boolean Move(String name, int x, int y) {
        return bombSprite.Move(name, x, y);
    }
    
    @Override
    public void moveAs(){
        bombSprite.moveAs();
    }

    @Override
    public void Tick(Graphics2D g2d) {
        bombSprite.DoTick(g2d);
    }

    @Override
    public int getX() {
        return bombSprite.getX();
    }

    @Override
    public int getY() {
        return bombSprite.getY();
    }

    @Override
    public boolean equals(CustomSprite otherEntry) {
        return false;
    }

    @Override
    public boolean equals(String otherEntry) {
        return false;

    }
    
}
    