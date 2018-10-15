/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
class Multi extends Thread {

    private Socket s = null;
    DataInputStream infromClient;

    Multi() throws IOException {

    }

    Multi(Socket s) throws IOException {
        this.s = s;
        infromClient = new DataInputStream(s.getInputStream());
    }

    public void run() {

        String SQL = new String();
        try {
            SQL = infromClient.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Query: " + SQL);
        try {
            System.out.println("Socket Closing");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class Connector {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        try {
            Socket socket = serverSocket.accept();
            while (true) {

                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(0);
                data_out.flush();
                TimeUnit.MILLISECONDS.sleep(35);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            serverSocket.close();
        }
    }
}
