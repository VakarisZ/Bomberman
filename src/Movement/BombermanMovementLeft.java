/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;

import Board.Map;
import Board.Point;
import Board.Sprites.CustomSprite;

/**
 *
 * @author Vakaris
 */
public class BombermanMovementLeft extends BombermanMovement{
    
    public BombermanMovementLeft(int speed, CustomSprite mover, Map map, int size){
        super(speed, mover, map, size);
    }
    
    @Override
    public void move() {
        // Bomberman's center point
        Point bp = new Point(this.mover.getX(), this.mover.getY());
        int d_x = 0;
        int d_y = 0;
        // Create 4 collision detection points
        Point[] points = Point.getCollisionDetectionPoints(bp.x,
                bp.y, this.size);
        Point p1, p2 = new Point(bp.x, bp.y);
        // If going left we take points to the left
        p1 = this.pointMovement(points[0], -1, 0);
        p2 = this.pointMovement(points[3], -1, 0);
        Point destination = Point.closerTo(p1, p2);
        bp.x = bp.x + destination.x;
        bp.y = bp.y + destination.y;
        this.mover.Move(bp.x, bp.y);
    }
}