package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Application extends JFrame {
	private Board b = new Board();
	
	public Board getBoard() {
		return b;
	}
	
    public Application() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(b);
        
        setSize(600, 400);
        setResizable(false);
        
        setTitle("Pong");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	
    	Song s =  new Song("Justin_Bieber_-_Baby_ft.wav");
    	Thread t = new Thread(s);
    	t.start();
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }

}