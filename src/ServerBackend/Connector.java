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
