/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Sprites;

import Client.ClientBoard;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import Board.Sprites.BombermanSprite;
import Movement.Movement;
import java.util.ArrayList;

/**
 *
 * @author mati
 */
public class BombermanEnemySpriteToCustomSpriteAdapter implements CustomSprite {

    private EnemyBombermanSprite bombermanSprite;
    
    public BombermanEnemySpriteToCustomSpriteAdapter(EnemyBombermanSprite bs) {
        bombermanSprite = bs;
    }
    
    @Override
    public void addMovement(Movement movement){
        bombermanSprite.addMove(movement);
    }

    @Override
    public void ChangeImage(Image image) {
        bombermanSprite.ChangeImage(image);
    }

    @Override
    public void Move(int x, int y) {
        bombermanSprite.Move(x, y);
    }

    @Override
    public boolean Move(String name, int x, int y) {
        return bombermanSprite.Move(name, x, y);
    }

    @Override
    public void moveAs(){
        bombermanSprite.moveAs();
    }
    
    @Override
    public void Tick(Graphics2D g2d) {
        bombermanSprite.Tick(g2d);
    }

    @Override
    public int getX() {
        return bombermanSprite.getX();
    }

    @Override
    public int getY() {
        return bombermanSprite.getY();
    }

    @Override
    public boolean equals(CustomSprite otherEntry) {
        if (otherEntry.getClass().getSuperclass() == BombermanSprite.class) {
            return bombermanSprite.equals(otherEntry);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(String otherEntry) {
        return bombermanSprite.equals(otherEntry);

    }

}
