/*
 gal iš čia konstruktorių parametrus perkelti į klases? kad būtų patogiau kviesti
 */
package Board.Obstacles;

/**
 * Bomb factory class
 * @author Linas
 */
public class BombFactory extends ObstacleFactory {

    @Override
    public Bomb createObstacle(ObstacleType obsType) {

        Bomb obs = null;

        switch (obsType) {
            case BigBomb:
                obs = new BigBomb(false, true, 4, 4.0f);
                break;
            case SmallBomb:
                obs = new SmallBomb(false, true, 2, 2.0f);
                break;
        }
        return obs;
    }

}
