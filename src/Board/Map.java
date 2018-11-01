/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import java.util.LinkedList;
import Board.Sprites.CustomSprite;

/**
 *
 * @author Vakaris
 */
public class Map {
    private static Map instance = null;
    private final Cell[][] mapCells;
    private LinkedList<CustomSprite> sprites;
    // We need to get this from db in future
    public final int BLOCK_SIZE = 32;
    public final int N_BLOCKS = 15;
    
    private Map() {
        mapCells = Cell.getMapCells(N_BLOCKS, N_BLOCKS, BLOCK_SIZE);
        sprites = new LinkedList<CustomSprite>();
        System.out.println("Singleton initialized");
    }
    
    public static synchronized Map getInstance() {
        if(instance == null){
            instance = new Map();
        }
        return instance;
    } 
    
    public void addSprite(CustomSprite sprite){
        this.sprites.add(sprite);
    }
    
    public LinkedList<CustomSprite> getSprites(){
        return this.sprites;
    }
    
    public Cell getMapCell(int x, int y){
        return this.mapCells[x][y];
    }
    
}
