/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Sprites;

import Client.ClientBoard;
import java.awt.Graphics2D;
import java.awt.Image;


/**
 * /**
 *
 * @author mati
 */
public interface CustomSprite {

  

    public void ChangeImage(Image image);

    
    public void Move(int x, int y);
    public boolean Move(String name, int x, int y);
    

    public void Tick(Graphics2D g2d);
    
    public int getX();
    
    public int getY();
   
    public boolean equals(CustomSprite otherEntry);
    
    public boolean equals(String otherEntry);
}
