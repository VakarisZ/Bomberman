/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

/**
 *
 * @author Vakaris
 */
public class PushLeft implements Expression {
    Expression next;
    int shift;
    Encryption enc;
    
    public PushLeft(int shift, Expression next){
        this.next = next;
        this.shift = shift;
        this.enc = new Encryption();
    }

    @Override
    public String encrypt() {
        return enc.encryptText(next.encrypt(), shift, false, true);
    }
}