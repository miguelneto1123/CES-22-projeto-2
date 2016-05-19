package ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Application extends JFrame {

    public Application() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(800, 600);
        setResizable(false);
        
        setTitle("Pong");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}