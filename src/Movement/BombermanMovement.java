/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;

import Board.Cell;
import Board.Sprites.CustomSprite;
import Board.Point;
import Board.Map;


/**
 *
 * @author Vakaris
 */
public class BombermanMovement extends Movement{
    
    int size;
    
    public BombermanMovement(int speed, CustomSprite mover,
            Map map, int size){
        super(speed, mover, map);
        this.size = size;
    }

    // Returns the biggest possible movement of one point in offsets
    // Eg. if point x,y could move 3 to the left it returns Point(-3,0)
    protected Point pointMovement(Point p, int req_dx, int req_dy) {
        int x = p.x;
        int y = p.y;
        // We find the cell we are in    
        int cell_x = (int) Math.floor((double) x / this.map.BLOCK_SIZE);
        int cell_y = (int) Math.floor((double) y / this.map.BLOCK_SIZE);
        java.util.Map<String, Boolean> current = this.map.getMapCell(cell_y, cell_x).getBorders();
        // We find out destination 
        int d_x, d_y = 0;
        d_x = (x + this.speed * req_dx);
        d_y = (y + this.speed * req_dy);
        int d_cell_x = (int) Math.floor((((double) d_x) / this.map.BLOCK_SIZE));
        int d_cell_y = (int) Math.floor((((double) d_y) / this.map.BLOCK_SIZE));
        // If try to go out of bounds
        if (d_cell_x < 0 || d_cell_x == this.map.N_BLOCKS || d_cell_y < 0 || d_cell_y == this.map.N_BLOCKS) {
            return new Point(0, 0);
        }

        java.util.Map<String, Boolean> destination = this.map.getMapCell(d_cell_y, d_cell_x).getBorders();
        // If destination cell is not the same as origin's
        if (cell_x != d_cell_x || cell_y != d_cell_y) {
            // Going right
            if (req_dx == 1) {
                if (current.get("right_b") || destination.get("left_b")) {
                    x = this.map.getMapCell(cell_y, cell_x).getX() + this.map.BLOCK_SIZE - 1;
                } else {
                    x = d_x;
                }
            }
            // Going left
            if (req_dx == -1) {
                if (current.get("left_b") || destination.get("right_b")) {
                    x = this.map.getMapCell(cell_y, cell_x).getX() + 1;
                } else {
                    x = d_x;
                }
            }
            // Going up
            if (req_dy == -1) {
                if (current.get("top_b") || destination.get("bottom_b")) {
                    y = this.map.getMapCell(cell_y, cell_x).getY() + 1;
                } else {
                    y = d_y;
                }
            }
            // Going down
            if (req_dy == 1) {
                if (current.get("bottom_b") || destination.get("top_b")) {
                    y = this.map.getMapCell(cell_y, cell_x).getY() + this.map.BLOCK_SIZE - 1;
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