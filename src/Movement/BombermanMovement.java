/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;

import Board.Cell;
import Board.Sprites.CustomSprite;
import Board.Point;
import java.util.Map;

/**
 *
 * @author Vakaris
 */
public class BombermanMovement extends Movement{
    
    int size;
    
    public BombermanMovement(int req_dx, int req_dy, int speed, CustomSprite mover,
            Cell[][] mapCells, int blockSize, int blockCount, int size){
        super(req_dx, req_dy, speed, mover, mapCells, blockSize, blockCount);
        this.size = size;
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
        // If going right we take right points
        if (req_dx == 1) {
            p1 = this.pointMovement(points[1]);
            p2 = this.pointMovement(points[2]);
        } else if (req_dx == -1) { // If moving left
            p1 = this.pointMovement(points[0]);
            p2 = this.pointMovement(points[3]);
        } else if (req_dy == 1) { // If moving down
            p1 = this.pointMovement(points[2]);
            p2 = this.pointMovement(points[3]);
        } else if (req_dy == -1) { // If moving up
            p1 = this.pointMovement(points[0]);
            p2 = this.pointMovement(points[1]);
        } else {
            return;
        }
        Point destination = Point.closerTo(p1, p2);
        bp.x = bp.x + destination.x;
        bp.y = bp.y + destination.y;
        this.mover.Move(bp.x, bp.y);
    }

    // Returns the biggest possible movement of one point in offsets
    // Eg. if point x,y could move 3 to the left it returns Point(-3,0)
    private Point pointMovement(Point p) {
        int x = p.x;
        int y = p.y;
        // We find the cell we are in    
        int cell_x = (int) Math.floor((double) x / this.blockSize);
        int cell_y = (int) Math.floor((double) y / this.blockSize);
        Map<String, Boolean> current = this.mapCells[cell_y][cell_x].getBorders();
        // We find out destination 
        int d_x, d_y = 0;
        d_x = (x + this.speed * req_dx);
        d_y = (y + this.speed * req_dy);
        int d_cell_x = (int) Math.floor((((double) d_x) / this.blockSize));
        int d_cell_y = (int) Math.floor((((double) d_y) / this.blockSize));
        // If try to go out of bounds
        if (d_cell_x < 0 || d_cell_x == this.blockCount || d_cell_y < 0 || d_cell_y == this.blockCount) {
            return new Point(0, 0);
        }

        Map<String, Boolean> destination = this.mapCells[d_cell_y][d_cell_x].getBorders();
        // If destination cell is not the same as origin's
        if (cell_x != d_cell_x || cell_y != d_cell_y) {
            // Going right
            if (req_dx == 1) {
                if (current.get("right_b") || destination.get("left_b")) {
                    x = this.mapCells[cell_y][cell_x].getX() + this.blockSize - 1;
                } else {
                    x = d_x;
                }
            }
            // Going left
            if (req_dx == -1) {
                if (current.get("left_b") || destination.get("right_b")) {
                    x = this.mapCells[cell_y][cell_x].getX() + 1;
                } else {
                    x = d_x;
                }
            }
            // Going up
            if (req_dy == -1) {
                if (current.get("top_b") || destination.get("bottom_b")) {
                    y = this.mapCells[cell_y][cell_x].getY() + 1;
                } else {
                    y = d_y;
                }
            }
            // Going down
            if (req_dy == 1) {
                if (current.get("bottom_b") || destination.get("top_b")) {
                    y = this.mapCells[cell_y][cell_x].getY() + this.blockSize - 1;
                } else {
                    y = d_y;
                }
            }
        } else {
            x = d_x;
            y = d_y;
        }
        return Point.distance(new Point(x, y), p);
    }
}
