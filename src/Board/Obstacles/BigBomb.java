package Board.Obstacles;

/**
 * Big Bomb class
 *
 * @author Linas
 */
public class BigBomb extends Bomb {

    public BigBomb(boolean destructable, boolean walkable, int explosionRadius,
            float explosionTimer, String clientString, String bombString, int x, int y) {
        super(destructable, walkable, explosionRadius, explosionTimer, clientString, bombString
        , x, y);
    }

}
