/*
 * Essa classe eh responsavel por detectar as colisoes. Vai servir de
 * superclasse pra fazer as raquetes e a bola 
 * */

package engine;

public class BoundingBox {
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		height = h;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public BoundingBox(int w, int h, int x, int y) {
		setWidth(w);
		setHeight(h);
		setX(x);
		setY(y);
	}
	
	public boolean detectCollision(BoundingBox b){
		return (x > b.getX() && x < b.getX() + b.getWidth()) || 
				(y > b.getY() && y < b.getY() + b.getHeight());
	}
	
	public boolean isInYLimits(int yMin, int yMax) {
		return y > yMax - height || y < yMin;
	}
}
