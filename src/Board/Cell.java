/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Vakaris
 */
public class Cell {
    private boolean top_b;
    private boolean left_b;
    private boolean right_b;
    private boolean bottom_b;
    
    private int x;
    private int y;
    
    Cell(int x, int y, boolean solid, int block_size){
        this.x = x * block_size;
        this.y = y * block_size;
        this.top_b = solid;
        this.right_b = solid;
        this.bottom_b = solid;
        this.left_b = solid;
        
    }

    public static Cell[][] getMapCells(int x, int y, int block_size){
        Cell[][] cells = new Cell[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(i % 2 != 0 && j % 2 != 0){
                    cells[i][j] = new Cell(j, i, true, block_size);
                } else {
                    cells[i][j] = new Cell(j, i, false, block_size);
                }
                if(i == 0){
                    cells[i][j].top_b = true;
                }
                if(i == x-1){
                    cells[i][j].bottom_b = true;
                }
                if(j == 0){
                    cells[i][j].left_b = true;
                }
                if(j == x-1){
                    cells[i][j].right_b = true;
                }
            }
        }
        return cells;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public Map<String, Boolean> getBorders(){
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("top_b", this.top_b);
        map.put("right_b", this.right_b);
        map.put("bottom_b", this.bottom_b);
        map.put("left_b", this.left_b);
        return map;
    }
    
}

