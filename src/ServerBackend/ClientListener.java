/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import Board.Obstacles.Bomb;
import Board.Obstacles.BombFactory;
import Board.Obstacles.ObstacleFactory;
import Board.Obstacles.ObstacleType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ClientListener extends Thread implements IObservable {

    public String clientName;
    DataInputStream infromClient;
    int req_dx, req_dy;
    int req_bomb;
    String clienString;
    IObserver observer;

    ClientListener() throws IOException {

    }

    ClientListener(Socket s, String clientname, IObserver o) throws IOException {
        infromClient = new DataInputStream(s.getInputStream());
        clienString = clientname;
        observer = o;
    }

    public void run() {
        while (true) {
            getReq();
        }

    }

    public void testBombs(int bombType) {
        ObstacleFactory bombFactory = new BombFactory();

//        System.out.println("Select : bi(G) bomb / (S)mall bomb / e(X)it");
        if (bombType == 1 || bombType == 2) {
            Bomb bomb = null;

            //BombFactory bfact = new BombFactory();
            if (bombType == 1) {
                bomb = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                //bomb = bfact.createObstacle("BigBomb");
            } else if (bombType == 2) {
                bomb = (Bomb) bombFactory.createObstacle(ObstacleType.SmallBomb);
            }
            
            System.out.println("Created " + bomb.getClass() + " with parameters:" + bomb.toString());
            Bomb bomb2 = bomb.shallowCopy();
                Bomb bomb3 = null;
                if (bombType == 1) {
                    bomb3 = (Bomb) bombFactory.createObstacle(ObstacleType.BigBomb);
                } else if (bombType == 2) {
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

        }

    }

    public void getReq() {
        try {
            req_bomb = infromClient.readInt();
            req_dx = infromClient.readInt();
            req_dy = infromClient.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (req_dx != 0 || req_dy != 0) {
            NotifyObserver(observer);
        }
        if (req_bomb != 0){
            testBombs(req_bomb);
        }

    }

    @Override
    public void NotifyObserver(IObserver o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        o.Update(clienString, req_dx, req_dy);
    }
}
