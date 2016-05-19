package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import engine.Ball;
import engine.Racket;

public class Board extends JPanel implements ActionListener {
	
	public int ballWidth = 16;
	public int ballHeight = 16;
	
	public int racketWidth = 16;
	public int racketHeight = 60;
	
	public int boardWidth = 800;
    public int boardHeight = 600;
    public int goalWidth = 16;
	
	private Timer timer;
	private Ball ball;
    private Racket leftRacket;
    private Racket rightRacket;
    private final int DELAY = 10;
    
        public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        
        ball = new Ball (ballWidth, ballHeight, boardWidth/2, boardHeight/2);
        leftRacket = new Racket(racketWidth, racketHeight, 
        		                goalWidth, boardHeight/2 - racketHeight/2, 'L');
        rightRacket = new Racket(racketWidth, racketHeight,
        		                 boardWidth - racketWidth - goalWidth,
        		                 boardHeight/2 - racketHeight/2, 'R');
        timer = new Timer(DELAY, this);
        timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);
        
        g2d.drawImage(leftRacket.getImage(), leftRacket.getX(), leftRacket.getY(), this); 
        
        g2d.drawImage(rightRacket.getImage(), rightRacket.getX(), rightRacket.getY(), this); 

      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	ball.detectBoundedCollision(0, boardHeight);
     	ball.applyBoundedCollision(0, boardHeight);
     	ball.applyRacketCollision(leftRacket);
     	ball.applyRacketCollision(rightRacket);
     	ball.leftGoal(goalWidth + racketWidth, boardWidth/2, boardHeight/2);
     	ball.rightGoal(boardWidth - goalWidth - racketWidth, boardWidth/2, boardHeight/2);
     	
    	ball.move();
    	leftRacket.move(0, boardHeight);
        rightRacket.move(0, boardHeight);
        repaint();
          
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	leftRacket.keyReleased(e);
        	rightRacket.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	leftRacket.keyPressed(e);
        	rightRacket.keyPressed(e);
        }
    }
}