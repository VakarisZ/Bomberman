/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ClientConnector extends Thread{

    private Queue<IState> clients = new ConcurrentLinkedQueue<IState>();
    int maxClients;
    ServerSocket serverSocket;
    IObserver obs;
    
    ClientConnector() throws IOException {

    }

    ClientConnector(int maxclients, Queue<IState> cl, IObserver o) throws IOException {
        clients = cl;
        maxClients = maxclients;
        obs = o;

    }

    public void run() {
        try {
            while (clients.size() < maxClients) {
                serverSocket = new ServerSocket(4000);
                Socket socket = serverSocket.accept();
                IState t = new StateAlive(socket, obs);
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
