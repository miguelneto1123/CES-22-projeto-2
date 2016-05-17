package engine;

import utils.*;

public class Racket extends BoundingBox {
	
	private int upSpeed = -4;
	private int downSpeed = 4;
	
	public Racket (int w, int h, int x, int y) {
		super(w, h, x, y);
	}
	
	public void setY(int y, int yMin, int yMax){
		if (isInYLimits(yMin, yMax)){
			this.y = y;
		}
		else {
			if (y < yMin) {
				this.y = yMin;
			} else if (y > yMax - height) {
				this.y = yMax - height;
			}
		}
	}
	
	public void move (Direction d, int yMin, int yMax){
		switch (d){
			case UP:
				setY(y + upSpeed, yMin, yMax);
				break;
			case DOWN:
				setY(y + downSpeed, yMin, yMax);
				break;
		}
	}
}
