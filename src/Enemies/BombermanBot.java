/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

/**
 *
 * @author Vakaris
 */
public class BombermanBot implements Enemy{
    public int x, y, speed;
    
    public BombermanBot(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void move() {
        // System.out.println("Bot walks closer, drops bomb and runs");
        walk(speed, true);
        dropBomb();
        walk(speed, false);
    }
    
    //Walks x steps either to or away from nearest bomberman
    private void walk(int steps, boolean closer){
        if (closer){
            this.x -= steps;
            this.y -= steps;
        } else {
            this.x += steps;
            this.y += steps;
        }
    }
    
    private void dropBomb(){
        
    }
}
