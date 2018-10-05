package Board.Obstacles;

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
        ObstacleFactory boxFactory = new BoxFactory();

        Scanner scanner = new Scanner(System.in);
        scanner.reset();

        String userInput = "";
        System.out.println("Select : bi(G) bomb / (S)mall bomb / e(X)it");
        userInput = scanner.nextLine();

        while (!userInput.equals("X")) {

            if (userInput.equals("G") || userInput.equals("S")) {
                Bomb bomb = null;
                
                //BombFactory bfact = new BombFactory();
                
                if (userInput.equals("G")) {
                    bomb = (Bomb) bombFactory.createObstacle("BigBomb");
                    //bomb = bfact.createObstacle("BigBomb");
                } else if (userInput.equals("S")) {
                    bomb = (Bomb) bombFactory.createObstacle("SmallBomb");
                }
                
                if (bomb != null) {
                    try {
                        bomb.drop();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AbstractFactoryTests.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            userInput = scanner.nextLine();
        }

        System.out.println("GAME OVER");
        scanner.close();

    }

}
