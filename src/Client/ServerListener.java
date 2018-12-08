/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ServerListener extends Thread{
    DataInputStream infromServer;
    ListenerInterface listener;
    
    ServerListener(DataInputStream inputStream, ListenerInterface o)
    {
        infromServer = inputStream;
        listener = o;
    }
    public void run()   {
        while (true)
        {
            try {
                int message = infromServer.readInt();
                int x;
                int y;
                String clientName;
                switch(message)
                {
                    case(0):
                        x = infromServer.readInt();
                        y = infromServer.readInt();
                        listener.moveSelf(x, y);
                        break;
                    case(1):
                        clientName = infromServer.readUTF();
                        x = infromServer.readInt();
                        y = infromServer.readInt();
                        listener.moveOther(clientName, x, y);
                        break;
                    case(2):
                        clientName = infromServer.readUTF();
                        x = infromServer.readInt();
                        y = infromServer.readInt();
                        listener.addEnemySprite(clientName, x, y);
                        listener.moveOther(clientName, x, y);
                    case(10):
                        x = infromServer.readInt();
                        y = infromServer.readInt();
                        clientName = infromServer.readUTF();
                        listener.addBomb(clientName, x, y);
                        
                        
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    
}
