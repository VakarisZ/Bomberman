package Board.Obstacles;

/**
 * Big Bomb class
 *
 * @author Linas
 */
public class BigBomb extends Bomb {

    public BigBomb(boolean destructable, boolean walkable, int explosionRadius,
            float explosionTimer, String clientString, int x, int y) {
        super(destructable, walkable, explosionRadius, explosionTimer, clientString
        , x, y);
    }

}
