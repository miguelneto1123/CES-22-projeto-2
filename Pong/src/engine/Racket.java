package engine;

public class Racket extends BoundingBox {
	private int upSpeed = -4;
	private int downSpeed = 4;
	
	public Racket (int w, int h, int x, int y) {
		super(w, h, x, y);
	}
}
