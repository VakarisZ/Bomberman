/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import Board.Board;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */




public class Connector {

    ClientConnector cc;// = new ClientConnector(2);
    Board board;
    public Graphics2D g2d_placeholder;
    Queue<MultiClient> clients;// = new ConcurrentLinkedQueue<MultiClient>();

    public Connector() {
    }

    public static void main(String[] args) {
        Connector c = new Connector();
        c.StartGame();

    }

    public void StartGame() {
        clients = new ConcurrentLinkedQueue<MultiClient>();
        board = new Board();
        board.initVariables();
        try {
            cc = new ClientConnector(5, clients);
            cc.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        PlayGame();
    }

    public void PlayGame() {
        while (true) {
            doPlayers();
        }
    }

    public void doPlayers() {
        for (MultiClient c : clients) {
            System.out.println("REQUESTING DIR");
            c.getReq();
            System.out.println("GOT DIR");
            board.req_dx = c.req_dx;
            board.req_dy = c.req_dy;
            board.bomberman_x = c.bomberman_x;
            board.bomberman_y = c.bomberman_y;
            board.playGame(g2d_placeholder);
            c.move(board.bomberman_x, board.bomberman_y, clients);
            System.out.println("STUFF");
        }
    }
}
