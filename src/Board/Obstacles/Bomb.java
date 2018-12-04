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
public class Bomb extends Obstacle implements Cloneable, Runnable {

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
            //dc.setExplosionTimer(new BombTimer(this.explosionTimer.getTimeUntilDetonation()));
            dc.setExplosionTimer(this.explosionTimer.clone());
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
    public void drop() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Bomb explosion method
     */
    public void explode() {
        System.out.println("Board.Obstacles.Bomb.explode()");
        setExploded(true);
    }

    @Override
    public String toString() {
        return "explosionRadius=" + explosionRadius
                + " | timeUntilDetonation=" + this.explosionTimer.getTimeUntilDetonation();
    }

    @Override
    public void run() {
        System.out.println("Board.Obstacles.Bomb.drop()");

        this.setPlanted(true);

        int detonateAfter = (int) (explosionTimer.getTimeUntilDetonation() * 1000);

        try {
            Thread.currentThread().sleep(detonateAfter);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }

        //explode
        this.explode();
    }

}
