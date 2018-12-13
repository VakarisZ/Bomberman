/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.util.HashMap;
/**
 *
 * @author Vakaris
 */
public class Encryption {
    
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public String encryptText(String input, int shift, boolean right, boolean encrypt){
        System.out.println(input);
        HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
        if (! right){
            shift = alphabet.length()-shift;
        }
        for(int i=0; i<alphabet.length(); i++){
            if (encrypt){
                alphaMap.put(alphabet.charAt(i), alphabet.charAt((i+shift)%alphabet.length()));
            } else {
                alphaMap.put(alphabet.charAt((i+shift)%alphabet.length()), alphabet.charAt(i));
            }
        }
        String inputText = input.toLowerCase();
        String outputText = "";
        for(int j=0; j<inputText.length(); j++){
            outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
        }
        System.out.println(outputText);
        return outputText;
    }
}
