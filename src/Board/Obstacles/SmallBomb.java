package Board.Obstacles;

/**
 * Small bomb class
 *
 * @author Linas
 */
public class SmallBomb extends Bomb {

    public SmallBomb(boolean destructable, boolean walkable, int explosionRadius,
            float explosionTimer, String clientString, String bombString, int x, int y) {
        super(destructable, walkable, explosionRadius, explosionTimer, clientString, bombString,
                x, y);
    }

}
