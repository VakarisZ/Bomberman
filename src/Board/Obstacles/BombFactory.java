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
    public Bomb createObstacle(String type) {

        Bomb obs = null;

        switch (type) {
            case "BigBomb":
                obs = new BigBomb(false, true, 4, 4.0f);
                System.out.println("weed");
                break;
            case "SmallBomb":
                obs = new SmallBomb(false, true, 2, 2.0f);
                break;
        }
        return obs;
    }

}
