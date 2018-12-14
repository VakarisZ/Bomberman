package Board.Flyweights;

import java.util.HashMap;

/**
 *
 * @author Linas
 */
public class FlyweightImageFactory {

    private static final HashMap flyweights = new HashMap();

    public static FlyweightImage getFlyweightImage(FlyweightImageKeys imageKey) {

        FlyweightImage flyweightImage = (FlyweightImage) flyweights.get(imageKey);

        if (flyweightImage == null) {

            switch (imageKey) {
                case BigBomb:
                    flyweightImage = new BigBombImage();
                    break;
                case SmallBomb:
                    flyweightImage = new BigBombImage();
                    break;
                case Player:
                    flyweightImage = new PlayerBaseImage();
                    break;
                case Enemy:
                    flyweightImage = new EnemyImage();
                    break;
                default:
                    flyweightImage = new NullObjectImage();
                    break;
            }
            flyweights.put(imageKey, flyweightImage);
            System.out.println("Creating BigBombImage Flyweight, with key: " + imageKey
                    + " | and Image object id:" + System.identityHashCode(((FlyweightImage) flyweights.get(imageKey)).getImage())
            );
            return flyweightImage;
        }
        System.out.println("Using " + imageKey + " flyweight Image"
                + " | and Image object id:" + System.identityHashCode(((FlyweightImage) flyweights.get(imageKey)).getImage())
        );
        return flyweightImage;
    }

}
