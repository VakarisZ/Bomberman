/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author Vakaris
 */
public class Point {
    public int x;
    public int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Returns corner points around x,y point
    public static Point[] getCollisionDetectionPoints(int x, int y, int offset){
        Point[] points = new Point[4];
        // Upper left
        points[0] = new Point(x-offset, y-offset);
        // Upper right
        points[1] = new Point(x+offset, y-offset);
        // Lower right
        points[2] = new Point(x+offset, y+offset);
        // Lower left
        points[3] = new Point(x-offset, y+offset);
        return points;
    }
    
    // Returns a point that contains distance between x1 and x2 and 
    // y1 and y2
    public static Point distance(Point p1, Point p2){
        int x = p1.x - p2.x;
        int y = p1.y - p2.y;
        return new Point(x,y);
    }
    
    // Chooses a point with smaller offsets
    public static Point closerTo(Point p1, Point p2){
        if(Math.abs(p1.x) < Math.abs(p2.x) || Math.abs(p1.y) < Math.abs(p2.y)){
            return p1;
        } else { // If they are equal we dont care witch one to return
            return p2;
        }
    }
}
