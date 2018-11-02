package Board.Obstacles;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Linas
 */
public class BombTimer implements Cloneable {

    private float timeUntilDetonation;

    public BombTimer(float timeUntilDetonation) {
        this.timeUntilDetonation = timeUntilDetonation;
    }

    public float getTimeUntilDetonation() {
        return timeUntilDetonation;
    }

    public void setTimeUntilDetonation(float timeUntilDetonation) {
        this.timeUntilDetonation = timeUntilDetonation;
    }

    public void start() throws InterruptedException {
        //convert seconds to miliseconds
        long miliseconds = (long) Math.ceil(timeUntilDetonation * 1000);
        System.out.println("waiting for " + timeUntilDetonation * 1000);
        // wait for explosion
        TimeUnit.MILLISECONDS.sleep(miliseconds);
        timeUntilDetonation = 0.0f;
    }

    public BombTimer clone() {
        try {
            return (BombTimer) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
