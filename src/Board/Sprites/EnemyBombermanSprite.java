/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Sprites;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author mati
 */
public class EnemyBombermanSprite extends BombermanDecorator{
    
    public EnemyBombermanSprite(BombermanSprite bs) {
        super(bs);
        Image enemyImage = new ImageIcon("images/bomber_enemy.png").getImage(); 
        ChangeImage(enemyImage);
    }
    
}
