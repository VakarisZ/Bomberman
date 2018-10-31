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
public class ClientListener extends Thread implements IObservable {

    public String clientName;
    DataInputStream infromClient;
    int req_dx, req_dy;
    String clienString;
    IObserver observer;

    ClientListener() throws IOException {

    }

    ClientListener(Socket s, String clientname, IObserver o) throws IOException {
        infromClient = new DataInputStream(s.getInputStream());
        clienString = clientname;
        observer = o;
    }

    public void run() {
        while (true){
            getReq();
        }

    }

    public void getReq() {
        try {
            req_dx = infromClient.readInt();
            req_dy = infromClient.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (req_dx != 0 || req_dy != 0){
            NotifyObserver(observer);
        }
        
    }

    @Override
    public void NotifyObserver(IObserver o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        o.Notify(clienString, req_dx, req_dy);
    }
}