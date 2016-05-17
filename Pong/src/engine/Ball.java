package engine;

import utils.*;

public class Ball extends BoundingBox {
	private int xSpeed = 2;
	private int ySpeed = 2;
	
	public Ball(int w, int h, int x, int y) {
		super(w, h, x, y);
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
		if(detectCollision(r))
			xSpeed *= -1;
	}
	
	public boolean leftGoal(int xMin, int xCenter, int yCenter){
		if (x < xMin) {
			setX(xCenter);
			setY(yCenter);
			return true;
		}
		return false;
	}
	
	public boolean rightGoal(int xMax, int xCenter, int yCenter){
		if (x > xMax) {
			setX(xCenter);
			setY(yCenter);
			return true;
		}
		return false;
	}
}
