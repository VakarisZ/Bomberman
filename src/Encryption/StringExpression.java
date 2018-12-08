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
public class StringExpression implements Expression{

    String data;
    
    public StringExpression(String data){
        this.data = data;
    }
    
    @Override
    public String encrypt() {
       return this.data;
    }
    
}
