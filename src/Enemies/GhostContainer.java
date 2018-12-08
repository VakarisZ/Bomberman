/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Vakaris
 */
public class GhostContainer implements EnemyIterator{
    int count;
    Ghost[] ghosts;
    
    public GhostContainer(int maxGhosts){
        this.ghosts = new Ghost[maxGhosts];
        count = 0;
    }
    
    public void AddGhost(int x, int y){
        Ghost ghost = new Ghost(x, y);
        ghosts[count] = ghost;
        count += 1;
    }

    @Override
    public Iterator createIterator() {
        return Arrays.asList(ghosts).iterator();
    }
}
