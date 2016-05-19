package engine;

import java.awt.event.KeyEvent;

import engine.BoundingBox;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Racket extends BoundingBox {
	
	private int upSpeed = -4;
	private int downSpeed = 4;
	private int dy = 0;
	private char Pos;
	private Image image;
	
	public Racket (int w, int h, int x, int y, char Position) {
		super(w,h,x,y);
		Pos = Position;
		ImageIcon ii = new ImageIcon("racket.png");
        image = ii.getImage();
	}
	
	public Image getImage() {
        return image;
    }
	
	public void setY(int y, int yMin, int yMax){
		if (isInYLimits(yMin, yMax)){
			this.y = y;
		}
		else {
			if (y < yMin) {
				this.y = yMin + 1;
			} else if (y > yMax - height) {
				this.y = yMax - height - 1;
			}
		}
	}
	
	public void move (int yMin, int yMax){
				setY(y + dy, yMin, yMax);
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

		if ((key == KeyEvent.VK_O && Pos == 'R') || (key == KeyEvent.VK_W && Pos == 'L')) {
            dy = upSpeed;
        }

        if ((key == KeyEvent.VK_L && Pos == 'R') || (key == KeyEvent.VK_S && Pos == 'L')) {
            dy = downSpeed;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_O && Pos == 'R') || (key == KeyEvent.VK_W && Pos == 'L')) {
            dy = 0;
        }

        if ((key == KeyEvent.VK_L && Pos == 'R') || (key == KeyEvent.VK_S && Pos == 'L')) {
            dy = 0;
        }
    }
    
}