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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
class MultiClient extends Thread {

    private Socket s = null;
    public String clientName;
    DataInputStream infromClient;
    DataOutputStream outtoClient;
    public int bomberman_x = 16;
    public int bomberman_y = 16;
    public int req_dx, req_dy;
    public int currentSpeed = 32;

    MultiClient() throws IOException {

    }

    MultiClient(Socket s) throws IOException {
        this.s = s;
        infromClient = new DataInputStream(s.getInputStream());
        outtoClient = new DataOutputStream(s.getOutputStream());
//        ServerSocket serverSocket = null;
//        int newSocketPort = 4001;
//        for (int i = 4001; i < 4200; i++){
//            if (usedsockets.contains(i)){
//                continue;
//            }
//            else
//            {
//                newSocketPort = i;
//                usedsockets.add(i);
//                outtoClient.writeInt(i);
//                outtoClient.flush();
//                serverSocket = new ServerSocket(newSocketPort);
//                break;
//            }
//        }
////        s.close();
////        ServerSocket serverSocket = new ServerSocket(newSocketPort);
//        Socket socket = serverSocket.accept();
//        infromClient = new DataInputStream(socket.getInputStream());
//        outtoClient = new DataOutputStream(socket.getOutputStream());
        
        
    }

    public void run() {
        try {
            clientName = infromClient.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Client name: " + clientName);

    }

    public void disconnect() {
        try {
            System.out.println("Socket Closing");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void move(int x, int y){
//        try {
//            outtoClient.writeInt(x);
//            outtoClient.writeInt(y);
//            outtoClient.writeByte(0);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        bomberman_x = x;
//        bomberman_y = y;
//        
//    }

    public void move(int x, int y, Queue<MultiClient> clients) {
        try {
            int clientsCount = clients.size() - 1;
            outtoClient.writeInt(x);
            outtoClient.writeInt(y);
            outtoClient.writeByte(clientsCount);
            for (MultiClient mc : clients) {
                if (mc.clientName != this.clientName) {
                    outtoClient.writeInt(mc.bomberman_x);
                    outtoClient.writeInt(mc.bomberman_y);
                }
            }
            outtoClient.flush();

        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        bomberman_x = x;
        bomberman_y = y;

    }

    public void getReq() {
        try {
            req_dx = infromClient.readInt();
            req_dy = infromClient.readInt();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ClientConnector extends Thread {

    private Queue<MultiClient> clients = new ConcurrentLinkedQueue<MultiClient>();
    int maxClients;
    ServerSocket serverSocket;

    ClientConnector() throws IOException {

    }

    ClientConnector(int maxclients, Queue<MultiClient> cl) throws IOException {
        // LinkedList<MultiClient> clients = new LinkedList<MultiClient>();
//        serverSocket = new ServerSocket(4000);
        clients = cl;
        maxClients = maxclients;
//        Socket socket = serverSocket.accept();
//        MultiClient t = new MultiClient(socket);
//        t.start();
//        clients.add(t);
//        serverSocket.close();
//        System.out.println("New client connected");
    }

    public void run() {

        try {
            while (clients.size() < maxClients) {
                serverSocket = new ServerSocket(4000);
                Socket socket = serverSocket.accept();
                MultiClient t = new MultiClient(socket);
                t.start();
                clients.add(t);
                System.out.println("New client connected");
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

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
            cc = new ClientConnector(10, clients);
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
