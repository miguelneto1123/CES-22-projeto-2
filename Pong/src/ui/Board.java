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
    
    private boolean gameStart;
    private boolean gamePaused;

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
        
        startGame();
        
        timer = new Timer(DELAY, this);
        timer.start();        
    }
    
    private void startGame() {
    	gameStart = true;
        gamePaused = true;
        ball.resetScores();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        if (gameStart) {
        	Font f = new Font("Verdana", Font.PLAIN, 20);
        	g2d.setFont(f);
        	g2d.setColor(Color.WHITE);
        	g2d.drawString("Press ENTER to start", leftScoreX - 20,
    				boardHeight / 2);
        }
        else {
        	if (gamePaused) {
	        	Font f = new Font("Verdana", Font.PLAIN, 20);
	        	g2d.setFont(f);
	        	g2d.setColor(Color.WHITE);
	        	g2d.drawString("Press ENTER to continue", leftScoreX - 20,
	        				boardHeight / 2);
	        }
	        if (!gameOver()){
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
	        	g2d.drawString("Left player wins!",
	        			leftScoreX, boardHeight / 2);
	        	g2d.drawString("Press ENTER to restart",
	        			leftScoreX - 20, boardHeight / 2 + 80);
	        }
	        
	        else if (ball.endGameRight()){
	        	Font f = new Font("Verdana", Font.PLAIN, 20);
	        	g2d.setFont(f);
	        	g2d.setColor(Color.WHITE);
	        	g2d.drawString("Right player wins!",
	        			leftScoreX, boardHeight / 2);
	        	g2d.drawString("Press ENTER to restart",
	        			leftScoreX - 20, boardHeight / 2 + 80);
	        }
        }
      
    }
    
    public void restartGame(KeyEvent e){
    	if (e.getKeyCode() == KeyEvent.VK_ENTER)
    		startGame();
    }
    
    
    public void pauseGame(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_ENTER)
    		gamePaused = true;
    }
    
    public void unpauseGame(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
    		gamePaused = false;
    		gameStart = false;
    	}
    }
    
    public boolean gameOver(){
    	return ball.endGameLeft() || ball.endGameRight();
    }
    
    public void gameUpdate(){
    	ball.applyBoundedCollision(0, boardHeight);
    	ball.applyRacketCollision(leftRacket);
    	ball.applyRacketCollision(rightRacket);
    	ball.leftGoal(0, boardWidth/2, boardHeight/2);
    	ball.rightGoal(boardWidth, boardWidth/2, boardHeight/2);
    	ball.move();
    	leftRacket.move(0, boardHeight);
        rightRacket.move(0, boardHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (!gameOver()){
    		if(!gamePaused)
    			gameUpdate();
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
        	if (gamePaused) {
        		unpauseGame(e);
        	}
        	else {
        		pauseGame(e);
        	}
        	if (gameOver()) {
        		restartGame(e);
        	}
        }
    }
}