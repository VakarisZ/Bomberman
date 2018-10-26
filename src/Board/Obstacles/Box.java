package Board.Obstacles;

/**
 *
 * @author Linas
 */
public class Box extends Obstacle {

    // how much does it take to destroy the box
    private int hardness;
    private int health;
    private boolean destroyed = false;

    public Box(boolean destructable, boolean walkable, int hardness) {
        super(destructable, walkable);
        this.hardness = hardness;
        this.health = hardness;
    }

    public int getHardness() {
        return hardness;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    //damages the box
    public int damageBox(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.destroyed = true;
        }
        return this.getHealth();
    }

}
