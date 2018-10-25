/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class MultiClient extends Thread {

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