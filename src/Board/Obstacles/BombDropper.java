/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board.Obstacles;
import Board.Board;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Vakaris
 */
public abstract class BombDropper extends Obstacle implements Runnable {
   
    public BombDropper(boolean destructable, boolean walkable, 
            String clientString, int x, int y){
        super(destructable, walkable);
        this.clientString = clientString;
        this.x = x;
        this.y = y;
    }
    
    // This should be refactored
    protected final int MAX_BOMBS = 3;
    public String clientString;
    public int x, y;
    
    public abstract void setPlanted(boolean planted);
    public abstract void setExploded(boolean exploaded);
    public abstract int getDetonationTime();
    public abstract boolean addToServer(ArrayList<Bomb> bombs, 
            String clientString, int x, int y);
    
    // explosion timer - time until the bomb explodes (SECONDS)
    private BombTimer explosionTimer;
    
    public final boolean dropBomb(ArrayList<Bomb> bombs){
        boolean added = addToServer(bombs, clientString, x, y);
        if (added){
            // System.out.println("Bomb dropped using TEMPLATE METHOD");
            this.setPlanted(true);
            Thread thread = new Thread(this);
            thread.start();
        }
        return added;
    }
    
    protected int countClientBombs(ArrayList<Bomb> bombs, String clientString){
        int bombCount = 0;
        for(Bomb bomb : bombs){
            if (bomb.clientString.equals(clientString)){
                bombCount += 1;
            }
        }
        return bombCount;
    }
    
    
    public void run() {

        int detonateAfter = getDetonationTime();

        try {
            Thread.currentThread().sleep(detonateAfter);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }

        //explode
        this.explode();
    }
    
    /**
     * Bomb explosion method
     */
    public void explode() {
        System.out.println("Board.Obstacles.Bomb.explode()");
        setExploded(true);
    }
}
