/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vakaris
 */
public class BotContainer implements EnemyIterator {
    private ArrayList<BombermanBot> bots;
    
    public BotContainer(){
        bots = new ArrayList<>();
    }
    
    public void addBot(int x, int y, int speed){
        BombermanBot bot = new BombermanBot(x, y, speed);
        bots.add(bot);
    }

    @Override
    public Iterator createIterator() {
        return bots.iterator();
    }
}
