
package Board.Obstacles;

/**
 * Abstract Factory of Obstacle objects
 * @author Linas
 */
public abstract class ObstacleFactory {
    /**
     * met
     * @param type
     * @return 
     */
    public abstract Obstacle createObstacle(String type) ;
}
