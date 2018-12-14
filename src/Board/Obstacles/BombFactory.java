package Board.Obstacles;

/**
 * Bomb factory class
 *
 * @author Linas
 */
public class BombFactory extends ObstacleFactory {

    /**
     * gal nenaudoti final, jei reikės pvz nuo powerupų pakeisti values, ir jei
     * bomb factory bus atskiras kiekvienam žaidėjui???
     */
    private final BigBomb bigBomb = new BigBomb(false, true, 4, 4.0f, "abc", 0, 0);
    private final SmallBomb smallBomb = new SmallBomb(false, true, 2, 2.0f, "abc", 0, 0);

    @Override
    public Bomb createObstacle(ObstacleType obsType) {

        Bomb obs = null;

        switch (obsType) {
            case BigBomb:
                //obs = new BigBomb(false, true, 4, 4.0f);
                obs = bigBomb.deepCopy();
                break;
            case SmallBomb:
                //obs = new SmallBomb(false, true, 2, 2.0f);
                obs = smallBomb.deepCopy();
                break;
        }
        return obs;
    }

}
