package Board.Obstacles;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for Abstract factories
 *
 * @author Linas
 */
public class AbstractFactoryTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ObstacleFactory bombFactory = new BombFactory();
        //ObstacleFactory boxFactory = new BoxFactory();

        Scanner scanner = new Scanner(System.in);
        scanner.reset();

        String userInput = "";
        System.out.println("Select : bi(G) bomb / (S)mall bomb / e(X)it");
        userInput = scanner.nextLine();

        while (!userInput.toUpperCase().equals("X")) {

            if (userInput.toUpperCase().equals("G") || userInput.toUpperCase().equals("S")) {
                Bomb bomb = null;

                //BombFactory bfact = new BombFactory();
                if (userInput.toUpperCase().equals("G")) {
                    bomb = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                    //bomb = bfact.createObstacle("BigBomb");
                } else if (userInput.toUpperCase().equals("S")) {
                    bomb = (Bomb) bombFactory.createObstacle(ObstacleType.SmallBomb);
                }

                //BOMB DROP TEST
                /*
                if (bomb != null) {
                    try {
                        bomb.drop();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AbstractFactoryTests.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 */
 /*
                System.out.println(bomb instanceof SmallBomb);
                System.out.println(bomb instanceof Bomb);
                System.out.println(bomb instanceof Obstacle);
                 */
                //PROTOTYPE TEST
                Bomb bomb2 = null;
                if (userInput.toUpperCase().equals("G")) {
                    bomb2 = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                } else if (userInput.toUpperCase().equals("S")) {
                    bomb2 = (Bomb) bombFactory.createObstacle(ObstacleType.SmallBomb);
                }
                Bomb bomb3 = bomb.deepCopy();
                System.out.println(System.identityHashCode(bomb.getExplosionTimer()));
                System.out.println(System.identityHashCode(bomb2.getExplosionTimer()));
                System.out.println(System.identityHashCode(bomb3.getExplosionTimer()));
                /*System.out.println(bomb.isPlanted());
                bomb.setPlanted(true);
                System.out.println(bomb.isPlanted());
                 */

            }

            userInput = scanner.nextLine();
        }

        System.out.println("GAME OVER");
        scanner.close();

    }

}
