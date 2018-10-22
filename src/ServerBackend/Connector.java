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
    public int bomberman_x = 15;
    public int bomberman_y = 15;
    public int req_dx, req_dy;
    public int currentSpeed = 32;

    MultiClient() throws IOException {

    }

    MultiClient(Socket s) throws IOException {
        this.s = s;
        infromClient = new DataInputStream(s.getInputStream());
        outtoClient = new DataOutputStream(s.getOutputStream());
    }
    
    public void run() {
        try {
            clientName = infromClient.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Client name" + clientName);

    }
    public void disconnect(){
                try {
            System.out.println("Socket Closing");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void move(int x, int y){
        try {
            outtoClient.writeInt(x);
            outtoClient.writeInt(y);
            outtoClient.writeByte(0);
            
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        bomberman_x = x;
        bomberman_y = y;
        
    }
    public void getReq(){
        try {
            req_dx = infromClient.readInt();
            req_dy = infromClient.readInt();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ClientConnector extends Thread {
    private LinkedList<MultiClient> clients = new LinkedList<MultiClient>();
    ClientConnector() throws IOException{
        
    }
    ClientConnector(int maxClients, LinkedList<MultiClient> cl) throws IOException{
        // LinkedList<MultiClient> clients = new LinkedList<MultiClient>();
        clients = cl;
           int max_clients = maxClients;
            while (clients.size() < max_clients) {
                ServerSocket serverSocket = new ServerSocket(4000);
                Socket socket = serverSocket.accept();
                MultiClient t = new MultiClient(socket);
                t.start();
                clients.add(t);
                System.out.println("New client connected");
                serverSocket.close();
    }

}
}


public class Connector {
    ClientConnector cc;// = new ClientConnector(2);
    Board board;
    public Graphics2D g2d_placeholder;
    private LinkedList<MultiClient> clients;
    public Connector(){}
    
    public static void main(String[] args)  {
        Connector c = new Connector();
        c.StartGame();
        
    }
    public void StartGame(){
        clients = new LinkedList<MultiClient>();
        board = new Board();
        board.initVariables();
        try {
            cc = new ClientConnector(1, clients);
        } catch (IOException ex) {
            Logger.getLogger(ClientConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        PlayGame();
    }
    public void PlayGame(){
        while (true){
            doPlayers();
        }
    }
    public void doPlayers(){
        for (MultiClient c : clients){
            System.out.println("REQUESTING DIR");
            c.getReq();
            System.out.println("GOT DIR");
            board.req_dx = c.req_dx;
            board.req_dy = c.req_dy;
            board.bomberman_x = c.bomberman_x;
            board.bomberman_y = c.bomberman_y;
            board.playGame(g2d_placeholder);
            c.move(board.bomberman_x, board.bomberman_y);
            System.out.println("STUFF");
        }
    }
}

