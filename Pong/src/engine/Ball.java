package engine;

import java.awt.Image;

import javax.swing.ImageIcon;

import utils.*;

public class Ball extends BoundingBox {
	private int xSpeed;
	private int ySpeed;
	private Image image;
	
	private int rightScore;
	private int leftScore;
	
	public Ball(int w, int h, int x, int y) {
		super(w, h, x, y);
		ImageIcon ii = new ImageIcon("ball.png");
        image = ii.getImage();
        
        resetSpeed();
        rightScore = 0;
        leftScore = 0;
	}
	
	private String getScoreImage(int s){
		return s + ".png";
	}
	
	public Image getRightScore(){
		return new ImageIcon(getScoreImage(rightScore)).getImage();
	}
	
	public Image getLeftScore(){
		return new ImageIcon(getScoreImage(leftScore)).getImage();
	}
	
	private void resetSpeed() {
		int multiplierX = 1, multiplierY = 1;
        
        if(Math.random() > 0.5){
        	multiplierX = -1;
        }
        if(Math.random() > 0.5){
        	multiplierY = -1;
        }
        
        xSpeed = 5 * multiplierX;
        ySpeed = 5 * multiplierY;
        
        randomSpeedY();
	}
	
	private void randomSpeedY(){
		int multiplierY = (int) Math.signum(ySpeed);
        
        if(Math.random() > 0.7){
        	multiplierY *= -1;
        }
		ySpeed = multiplierY * (1 + (int) (Math.random()*3));
	}
	
	public Image getImage() {
        return image;
    }
	
	public void move() {
		setX(x + xSpeed);
		setY(y + ySpeed);
		
	}
	
	public boolean detectBoundedCollision(int yMin, int yMax){
		return y < yMin || y > yMax - height;
	}
	
	public void applyBoundedCollision(int yMin, int yMax){
		if (detectBoundedCollision(yMin, yMax))
			ySpeed *= -1;
	}
	
	public void applyRacketCollision(Racket r){
		if(detectCollision(r)){
			xSpeed *= -1;
			randomSpeedY();
			int multiplier = 1;
			if (r.getPos() == 'L'){
				multiplier *= -1;
			}
			setX(r.getX() - multiplier*width);
		}
	}
	
	public boolean leftGoal(int xMin, int xCenter, int yCenter){
		if (x < xMin) {
			setX(xCenter);
			setY(yCenter);
			rightScore++;
			resetSpeed();
			return true;
		}
		return false;
	}
	
	public boolean rightGoal(int xMax, int xCenter, int yCenter){
		if (x > xMax) {
			setX(xCenter);
			setY(yCenter);
			leftScore++;
			resetSpeed();
			return true;
		}
		return false;
	}
	
	public boolean endGameLeft(){
		return leftScore > 6;
	}
	
	public boolean endGameRight(){
		return rightScore > 6;
	}
	
	public void resetScores(){
		rightScore = 0;
		leftScore = 0;
	}
}
