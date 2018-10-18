
package Board.Obstacles;

/**
 *
 * @author Linas
 */
public class BoxFactory extends ObstacleFactory{

    @Override
    public Box createObstacle(ObstacleType obsType) {
        return new Box(true, false, 1);
    }
    
}
