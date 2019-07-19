
public class PixelatedSquare {
	private int colorNum,
				xDim,
				yDim;
	private boolean figura;//Representa si el cuadrado es parte de la figura
	
	public PixelatedSquare(int colorNum, int x, int y) {
		this.colorNum = colorNum;
		this.xDim = x;
		this.yDim = y;
		this.figura = false;
	}
	
	public int getColorNum() {
		return this.colorNum;
	}
	
	public int getX() {
		return this.xDim;
	}
	
	public int getY() {
		return this.yDim;
	}
	
	public boolean getFigura() {
		return this.figura;
	}
	
	public void setColorNum(int colorNum) {
		this.colorNum = colorNum;
	}
	
	public void setX(int xDim) {
		this.xDim = xDim;
	}
	
	public void setY(int yDim) {
		this.yDim = yDim;
	}
	
	public void setFigura(boolean figura) {
		this.figura = figura;
	}

}
