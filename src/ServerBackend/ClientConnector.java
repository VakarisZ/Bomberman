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
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ClientConnector extends Thread implements Observer{

    private Queue<MultiClient> clients;// = new ConcurrentLinkedQueue<MultiClient>();
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

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
