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
public class StateDead extends Thread implements IState{

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

    @Override
    public int get_bomberman_x ()
    {
    return bomberman_x;
    }
    @Override
     public int get_bomberman_y ()
    {
    return bomberman_y;
    }
    @Override
    public void set_bomberman_x(int x)
    {
            bomberman_x = x;
    }
    @Override
    public void set_bomberman_y(int y)
    {
            bomberman_y = y;
    }
    StateDead() throws IOException {

    }
    
    StateDead(Socket s, IObserver o) throws IOException {
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
        
    }
    
    public void addBomb(Bomb bomb){
        bombs.add(bomb);
        try {
            outtoClient.writeInt(10);
            outtoClient.writeInt(bomb.x);
            outtoClient.writeInt(bomb.y);
            outtoClient.writeUTF(bomb.clientString);
            outtoClient.flush();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MoveKnownClient(String clientString, int bx, int by)
    {

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

    }
}