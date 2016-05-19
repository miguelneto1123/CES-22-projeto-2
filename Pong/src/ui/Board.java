package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import engine.Ball;
import engine.Racket;

public class Board extends JPanel implements ActionListener {
	
	private int ballWidth = 16;
	private int ballHeight = 16;
	
	private int racketWidth = 16;
	private int racketHeight = 60;
	
	private int boardWidth = 600;
    private int boardHeight = 400;
    private int goalWidth = 16;
    
    private int leftScoreX = (boardWidth / 2) - 82;
    private int rightScoreX = (boardWidth / 2) + 50;
    private int scoreY = 20;
	
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
        setBackground(Color.BLACK);
        
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
        
        if (!ball.endGameLeft() && !ball.endGameRight()){
            g2d.drawImage(leftRacket.getImage(), leftRacket.getX(),
            	leftRacket.getY(), this); 
            
            g2d.drawImage(rightRacket.getImage(), rightRacket.getX(),
            	rightRacket.getY(), this);
            
            g2d.drawImage(ball.getLeftScore(), leftScoreX, scoreY, this);
            
            g2d.drawImage(ball.getRightScore(), rightScoreX, scoreY, this);
            
            g2d.setColor(Color.WHITE);
            
            g2d.drawLine(boardWidth / 2, 0, boardWidth / 2, boardHeight);
            
            g2d.drawImage(ball.getImage(), ball.getX(),
                	ball.getY(), this);
        }
        
        else if (ball.endGameLeft()){
        	Font f = new Font("Verdana", Font.PLAIN, 20);
        	g2d.setFont(f);
        	g2d.setColor(Color.WHITE);
        	g2d.drawString("Left player wins!", leftScoreX, boardHeight / 2);
        }
        
        else if (ball.endGameRight()){
        	Font f = new Font("Verdana", Font.PLAIN, 20);
        	g2d.setFont(f);
        	g2d.setColor(Color.WHITE);
        	g2d.drawString("Right player wins!", leftScoreX, boardHeight / 2);
        }
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (!ball.endGameLeft() && !ball.endGameRight()){
	    	ball.applyBoundedCollision(0, boardHeight);
	    	ball.applyRacketCollision(leftRacket);
	    	ball.applyRacketCollision(rightRacket);
	    	ball.leftGoal(0, boardWidth/2, boardHeight/2);
	    	ball.rightGoal(boardWidth, boardWidth/2, boardHeight/2);
	    	ball.move();
	    	leftRacket.move(0, boardHeight);
	        rightRacket.move(0, boardHeight);
    	}
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