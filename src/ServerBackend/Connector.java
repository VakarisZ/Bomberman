/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import Board.Board;
import com.sun.media.sound.WaveFloatFileReader;
import java.awt.Graphics2D;
import Board.Obstacles.Bomb;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import Encryption.*;

/**
 *
 * @author mati
 */

public class Connector implements IObserver {
    Lock lock = new ReentrantLock();
    ArrayList<Bomb> bombs = new ArrayList<>();
    ClientConnector cc;// = new ClientConnector(2);
    Board board;
    public Graphics2D g2d_placeholder;
    private int memento_test = -1;
    private MultiClientMemento savedState;
//    Queue<MultiClient> clients;// = new ConcurrentLinkedQueue<MultiClient>();
    Queue<IState> clients = new ConcurrentLinkedQueue<>();
    public Connector() {
    }

    public static void main(String[] args) {
        Connector c = new Connector();
        c.StartGame();

    }

    public void StartGame() {
        board = new Board();
        board.initVariables();
        try {
            cc = new ClientConnector(5, clients, this);
            cc.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Bomb DropBomb(String clientString, int req_bomb){
        boolean dropped = false;
        Bomb bomb = null;
        for (IState c : clients){
            if (c.isClientName(clientString))
            {
                bomb = new Bomb(false, true, 2, 2.0f, clientString,
                        c.get_bomberman_x(), c.get_bomberman_y());
                dropped = bomb.dropBomb(bombs);
            }
        }
        if (dropped){
            return bomb;
        } else {
            return null;
        }
    }
    
    public boolean Move(String clientString, int req_dx, int req_dy)
    {
        for (IState c : clients){
            if (c.isClientName(clientString))
            {
                board.req_dx = req_dx;
                board.req_dy = req_dy;
                board.bomberman_x = c.get_bomberman_x();
                board.bomberman_y = c.get_bomberman_y();
                board.playGame(g2d_placeholder);
                if (board.bomberman_x != c.get_bomberman_x() || 
                        board.bomberman_y != c.get_bomberman_y())
                {
                    c.set_bomberman_x(board.bomberman_x);
                    c.set_bomberman_x(board.bomberman_y);                    
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void Update(IObservable o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(String clientString, int req_dx, int req_dy, int req_bomb) {
        board.moveEnemies();
        lock.lock();
        if (Move(clientString, req_dx, req_dy))
        {
           
            for (IState c : clients)
            {
                if (c.isClientName(clientString))
                {
                    c.moveSelf(board.bomberman_x, board.bomberman_y);
                    if (memento_test == 15) 
                    {
                        memento_test--;
                        savedState = c.saveState();
                    }
                    else if (memento_test < 15 && memento_test != 0){
                        memento_test--;
                    }
                    else if (memento_test == 0){
                        c.setState(savedState);
                    }
                    
                    break;
                }
            }
            NotifyAll(clientString);
        }
        if (req_bomb != 0)
        {
            Bomb bomb = DropBomb(clientString, req_bomb);
            if (bomb != null){
                for (IState c : clients)
                {
                    c.addBomb(bomb);
//                    break;
                }
            }
        }
        lock.unlock();
    }

    public void NotifyAll(String clientString) {
        for (IState c : clients)
            {
                if (!c.isClientName(clientString))
                {
                    c.MoveKnownClient(clientString, board.bomberman_x, board.bomberman_y);
                }
            }

    }
}
