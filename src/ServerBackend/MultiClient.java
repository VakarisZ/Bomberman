/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import Board.Obstacles.Bomb;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import Encryption.*;

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
    public ClientListener cl;
    IObserver obs;
    public ArrayList<Bomb> bombs = new ArrayList<>();

    MultiClient() throws IOException {

    }
    
    MultiClient(Socket s, IObserver o) throws IOException {
        this.s = s;
        obs = o;
        infromClient = new DataInputStream(s.getInputStream());
        outtoClient = new DataOutputStream(s.getOutputStream());
    }
    public MultiClientMemento saveState(){
        return new MultiClientMemento(bomberman_x, bomberman_y);
    }
    public void setState(MultiClientMemento state){
        int [] coordinates = state.getSavedState();
        bomberman_x = coordinates[0];
        bomberman_y = coordinates[1];
        moveSelf(bomberman_x, bomberman_y);
    }
    public void run() {
        try {
            clientName = infromClient.readUTF();
            cl = new ClientListener(s, clientName, obs);
            cl.start();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Client name: " + clientName);

    }
    
    public boolean isClientName(String otherClientName){
        return (clientName.equals(otherClientName));
    }

    public void disconnect() {
        try {
            System.out.println("Socket Closing");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void moveSelf(int x, int y)
    {
        bomberman_x = x;
        bomberman_y = y;
        try {
            outtoClient.writeInt(0);
            outtoClient.writeInt(x);
            outtoClient.writeInt(y);
            outtoClient.flush();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addBomb(Bomb bomb){
        bombs.add(bomb);
        try {
            /*
            System.out.println("Encrypting client string "+bomb.clientString);
            Expression clientString = new StringExpression(bomb.clientString);
            Expression e1 = new PushRight(5, clientString);
            Expression e2 = new PushRight(3, e1);
            Expression e3 = new PushLeft(15, e2);
            String secure = e3.encrypt();
            System.out.println("Encrypted client string "+secure);
            
            Encryption encryption = new Encryption();
            String d1 = encryption.encryptText(secure, 15, false, false);
            String d2 = encryption.encryptText(d1, 3, true, false);
            String decrypted = encryption.encryptText(d2, 5, true, false);
            System.out.println("Decrypted client string "+decrypted);
            */
            outtoClient.writeInt(10);
            outtoClient.writeInt(bomb.x);
            outtoClient.writeInt(bomb.y);
            outtoClient.writeUTF(bomb.clientString);
            outtoClient.flush();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void move(int x, int y, Queue<MultiClient> clientsMoved) {
//        try {
//            int clientsCount = clientsMoved.size() - 1;
//            outtoClient.writeInt(x);
//            outtoClient.writeInt(y);
//            outtoClient.writeByte(clientsCount);
//            for (MultiClient mc : clientsMoved) {
//                if (mc.clientName != this.clientName) {
//                    outtoClient.writeInt(mc.bomberman_x);
//                    outtoClient.writeInt(mc.bomberman_y);
//                }
//            }
//            outtoClient.flush();
//
//        } catch (IOException ex) {
//            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        bomberman_x = x;
//        bomberman_y = y;
//
//    }
    
    public void MoveKnownClient(String clientString, int bx, int by)
    {
        try {
            outtoClient.writeInt(1);
            outtoClient.writeUTF(clientString);
            outtoClient.writeInt(bx);
            outtoClient.writeInt(by);
            outtoClient.flush();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getReq() {
        try {
            req_dx = infromClient.readInt();
            req_dy = infromClient.readInt();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void MoveUnknownClient(String clientString, int bx, int by)
    {
        if (isClientName(clientString))
        {
            moveSelf(bx, by);
        }
        else
        {
            MoveKnownClient(clientString, bx, by);
        }
    }
}