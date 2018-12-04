package Board.Obstacles;

import java.io.Console;
import java.util.ArrayList;
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

                System.out.println("\nABSTRACT FACTORY START");
                //BombFactory bfact = new BombFactory();
                if (userInput.toUpperCase().equals("G")) {
                    bomb = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                    //bomb = bfact.createObstacle("BigBomb");
                } else if (userInput.toUpperCase().equals("S")) {
                    bomb = (Bomb) bombFactory.createObstacle(ObstacleType.SmallBomb);
                }
                System.out.println("Created " + bomb.getClass() + " with parameters:" + bomb.toString());
                System.out.println("ABSTRACT FACTORY END\n");

                // <editor-fold defaultstate="collapsed" desc="Bomb drop test">
                //BOMB DROP TEST
                if (bomb != null) {
                    bomb.drop();
                }
                // </editor-fold>
                //<editor-fold defaultstate="collapsed" desc="bomb instace class test">
                /*
            System.out.println(bomb instanceof SmallBomb);
            System.out.println(bomb instanceof Bomb);
            System.out.println(bomb instanceof Obstacle);
                 */
                //</editor-fold>
                /*
                System.out.println("\nPROTOTYPE START");
                //PROTOTYPE TEST
                Bomb bomb2 = bomb.shallowCopy();
                Bomb bomb3 = null;
                if (userInput.toUpperCase().equals("G")) {
                    bomb3 = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                } else if (userInput.toUpperCase().equals("S")) {
                    bomb3 = (Bomb) bombFactory.createObstacle(ObstacleType.SmallBomb);
                }
                System.out.println("explosionTimer id:"
                        + System.identityHashCode(bomb.getExplosionTimer())
                        + " | Bomb (original)       ("
                        + System.identityHashCode(bomb) + ")");
                System.out.println("explosionTimer id:"
                        + System.identityHashCode(bomb2.getExplosionTimer())
                        + " | Bomb2 (shallow copy)  ("
                        + System.identityHashCode(bomb2) + ")");
                System.out.println("explosionTimer id:"
                        + System.identityHashCode(bomb3.getExplosionTimer())
                        + " | Bomb3 (deep copy)     ("
                        + System.identityHashCode(bomb3) + ")");
                System.out.println("PROTOTYPE END\n");
                 */
                //<editor-fold defaultstate="collapsed" desc="Prototype measurements">
                // prototype measurements
                /*
                System.gc();
                System.gc();
                int n = 10000000;

                measureDeepCopies(n, bomb);
                measureShallowCopies(n, bomb);
                 */
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Bomb plant test">
                /*System.out.println(bomb.isPlanted());
            bomb.setPlanted(true);
            System.out.println(bomb.isPlanted());
                 */
                //</editor-fold>
            }

            userInput = scanner.nextLine();
        }

        System.out.println("GAME OVER");
        scanner.close();

    }

    public static void measureShallowCopies(int n, Bomb target) {
        ArrayList<Bomb> clones = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            clones.add(target.shallowCopy());
        }

        long stopTime = System.currentTimeMillis();
        long duration = stopTime - startTime;
        System.out.println("Shallow copy measurement with n = " + n + " elements:\n " + duration + " ms");
        clones = null;
        clones = new ArrayList<>();
        System.gc();
        System.gc();
    }

    private static void measureDeepCopies(int n, Bomb target) {
        ArrayList<Bomb> clones = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            clones.add(target.deepCopy());
        }

        long stopTime = System.currentTimeMillis();
        long duration = stopTime - startTime;
        System.out.println("Deep copy measurement with n = " + n + " elements:\n " + duration + " ms");
        clones = null;
        clones = new ArrayList<>();
        System.gc();
        System.gc();
    }

}
