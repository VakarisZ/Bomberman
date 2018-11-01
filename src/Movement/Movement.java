/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;
import Board.Sprites.CustomSprite;
import Board.Map;

/**
 *
 * @author Vakaris
 */
public class Movement {
    int speed;
    CustomSprite mover;
    Map map;
    
    public Movement(int speed, CustomSprite mover, Map map){
        this.speed = speed;   // How many pixels to go. Use blockSize to move whole block
        this.mover = mover;   // Custom sprite of mover
        this.map = map;
    }
    
    public void move(){
        mover.Move(mover.getX()+(this.speed), 
                mover.getY()+(this.speed));
    }
}
