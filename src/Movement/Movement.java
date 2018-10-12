/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;
import Board.CustomSprite;
import Board.Cell;

/**
 *
 * @author Vakaris
 */
public class Movement {
    int req_dx, req_dy, speed;
    CustomSprite mover;
    // Visi sitie turi buti singletonas:
    Cell[][] mapCells;
    int blockSize, blockCount;
    
    public Movement(int req_dx, int req_dy, int speed, CustomSprite mover,
            Cell[][] mapCells, int blockSize, int blockCount){
        this.req_dx = req_dx; // Goes left if -1 or right if 1
        this.req_dy = req_dy; // Goes up if -1 or down ir 1
        this.speed = speed;   // How many pixels to go. Use blockSize to move whole block
        this.mover = mover;   // Custom sprite of mover
        this.mapCells = mapCells; // Map cells. Should be in map singleton
        this.blockSize = blockSize; // Block size in pixels. Should be in map singleton
        this.blockCount = blockCount; // How many 
    }
    
    public void move(){
        mover.Move(mover.getX()+(req_dx*this.speed), 
                mover.getY()+(req_dy*this.speed));
    }
}
