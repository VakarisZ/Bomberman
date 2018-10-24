/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author Vakaris
 */
import Client.ClientBoard;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Bomberman extends JFrame {

    public Bomberman() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new ClientBoard());
        
        setTitle("Bomberman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 560);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Bomberman ex = new Bomberman();
            ex.setVisible(true);
        });
    }
}
