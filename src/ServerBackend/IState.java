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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturas
 */
public interface IState extends Runnable {
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author Arturas
 */
    public int get_bomberman_x();
    public int get_bomberman_y();
    public void set_bomberman_x(int x);
    public void set_bomberman_y(int y);
    public MultiClientMemento saveState();
    public void setState(MultiClientMemento state);
    public boolean isClientName(String otherClientName);
    public void disconnect();
    public void moveSelf(int x, int y);
    public void addBomb(Bomb bomb);
    public void MoveKnownClient(String clientString, int bx, int by);
    public void getReq();
    public void MoveUnknownClient(String clientString, int bx, int by);
    public void start();
}

