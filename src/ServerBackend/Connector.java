/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import Board.Board;
import com.sun.media.sound.WaveFloatFileReader;
import java.awt.Graphics2D;
import java.io.DataInputStream;
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

/**
 *
 * @author mati
 */




public class Connector implements IObserver {
    Lock lock = new ReentrantLock();

    ClientConnector cc;// = new ClientConnector(2);
    Board board;
    public Graphics2D g2d_placeholder;
//    Queue<MultiClient> clients;// = new ConcurrentLinkedQueue<MultiClient>();
    Queue<MultiClient> clients = new ConcurrentLinkedQueue<MultiClient>();
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
    
    public boolean Move(String clientString, int req_dx, int req_dy)
    {
        for (MultiClient c : clients){
            if (c.isClientName(clientString))
            {
                board.req_dx = req_dx;
                board.req_dy = req_dy;
                board.bomberman_x = c.bomberman_x;
                board.bomberman_y = c.bomberman_y;
                board.playGame(g2d_placeholder);
                if (board.bomberman_x != c.bomberman_x || 
                        board.bomberman_y != c.bomberman_y)
                {
                    c.bomberman_x = board.bomberman_x;
                    c.bomberman_y = board.bomberman_y;                    
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
    public void Notify(String clientString, int req_dx, int req_dy) {
        lock.lock();
        if (Move(clientString, req_dx, req_dy))
        {
            
            for (MultiClient c : clients)
            {
                if (c.isClientName(clientString))
                {
                    c.moveSelf(board.bomberman_x, board.bomberman_y);
                    break;
                }
            }
            NotifyAll(clientString);
            
            
        }
        lock.unlock();
    }

    @Override
    public void NotifyAll(String clientString) {
        for (MultiClient c : clients)
            {
                if (!c.isClientName(clientString))
                {
                    c.MoveKnownClient(clientString, board.bomberman_x, board.bomberman_y);
                }
            }

    }
}
