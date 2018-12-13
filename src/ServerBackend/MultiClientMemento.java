/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

/**
 *
 * @author mati
 */
public class MultiClientMemento {
    
    private final int bomberman_x, bomberman_y;
    
    public MultiClientMemento(int x, int y) {
        this.bomberman_x = x;
        this.bomberman_y = y;
    }
    public int [] getSavedState()
    {
        return new int[] {bomberman_x, bomberman_y};
    }
    
}
