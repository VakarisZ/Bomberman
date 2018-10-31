package Board.Obstacles;

// TimeUnit - used for delay
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bomb class
 *
 * @author Linas
 */
public class Bomb extends Obstacle implements Cloneable {

    // explosion radius - how many blocks in all directions will the explosion affect
    private int explosionRadius;
    // explosion timer - time until the bomb explodes (SECONDS)
    private BombTimer explosionTimer;

    // is the bomb planted (dropped)
    private boolean planted = false;
    // is the bomb planted (dropped)
    private boolean exploded = false;

    public Bomb(boolean destructable, boolean walkable,
            int explosionRadius, float timeUntilDetonation) {
        super(destructable, walkable);
        this.explosionRadius = explosionRadius;
        this.explosionTimer = new BombTimer(timeUntilDetonation);
    }

    /**
     * *
     * shallow copy of bomb object
     *
     * @return
     */
    public Bomb shallowCopy() {
        try {
            return (Bomb) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * deep copy - so far not needed
     *
     * @return
     */
    public Bomb deepCopy() {
        try {
            Bomb dc = (Bomb) super.clone();
            dc.setExplosionTimer(new BombTimer(this.explosionTimer.getTimeUntilDetonation()));
            return dc;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     public BombTimer getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(BombTimer explosionTimer) {
        this.explosionTimer = explosionTimer;
    }
    
    public boolean isPlanted() {
        return planted;
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    /**
     * Bomb dropping method
     *
     * @throws java.lang.InterruptedException
     */
    public void drop() throws InterruptedException {
        System.out.println("Board.Obstacles.Bomb.drop()");

        this.setPlanted(true);

        explosionTimer.start();

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
