
package Board.Obstacles;

/**
 *
 * @author Linas
 */
public class Box extends Obstacle{
    
    // how much does it take to destroy the box
    private int hardness;
    private int health;
    
    public Box(boolean destructable, boolean walkable, int hardness) {
        super(destructable, walkable);
        this.hardness = hardness;
        this.health = hardness;
    }
    
}
