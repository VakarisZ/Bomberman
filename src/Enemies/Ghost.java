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
public class Ghost implements Enemy {

    private int x,y;
    public Ghost(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void move() {
        followBomberman();
    }
    
    private void followBomberman(){
        //System.out.println("Ghost is moving to the closest bomberman");
    }
}
