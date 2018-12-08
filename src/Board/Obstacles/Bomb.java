package Board.Obstacles;

// TimeUnit - used for delay
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bomb class
 *
 * @author Linas
 */
public class Bomb extends BombDropper implements Cloneable {

    // explosion radius - how many blocks in all directions will the explosion affect
    private int explosionRadius;
    // explosion timer - time until the bomb explodes (SECONDS)
    private BombTimer explosionTimer;
    // How do we know who put the bomb?

    // is the bomb planted (dropped)
    private boolean planted = false;
    // is the bomb planted (dropped)
    private boolean exploded = false;

    public Bomb(boolean destructable, boolean walkable,
            int explosionRadius, float timeUntilDetonation, String clientString,
            int x, int y) {
        super(destructable, walkable, clientString, x, y);
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

    public boolean isExploded() {
        return exploded;
    }

    @Override
    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
    
    @Override
    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    @Override
    public int getDetonationTime(){
        return (int) (explosionTimer.getTimeUntilDetonation() * 1000);
    }
    
    @Override
    public String toString() {
        return "explosionRadius=" + explosionRadius
                + " | timeUntilDetonation=" + this.explosionTimer.getTimeUntilDetonation();
    }

    @Override
    public boolean addToServer(ArrayList<Bomb> bombs, 
            String clientString, int x, int y){
        if(super.countClientBombs(bombs, clientString) < MAX_BOMBS){
            Bomb bomb = new Bomb(false, true, 2, 2.0f, clientString, x, y);
            bombs.add(bomb);
            return true;
        } else {
            return false;
        }
    }
    
}
