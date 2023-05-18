/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * XTanks main class. Run this to start the players client.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

public class XTanks extends JFrame{
	
    
    public XTanks() {

        initUI();
    }

    /**
     * Initializes the UI
     */
    private void initUI() {
    	View v = new View();

        add(v);

        setSize(500, 500);

        setTitle("XTanks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
            XTanks ex = new XTanks();
            ex.setVisible(true);
    }
 



}
