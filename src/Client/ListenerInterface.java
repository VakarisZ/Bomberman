/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author mati
 */
public interface ListenerInterface {
    public void moveOther(String clientName, int x, int y);
    public void moveSelf(int x, int y);
    public void addEnemySprite(String clientName, int x, int y);
    public void addBomb(String clientName, int x, int y);
}
