
package Board.Obstacles;

/**
 *
 * @author Linas
 */
public class BoxFactory extends ObstacleFactory{

    @Override
    public Box createObstacle(String type) {
        return new Box(true, false, 1);
    }
    
}
