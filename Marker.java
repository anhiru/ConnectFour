import java.awt.Color;

public class Marker {
	private int row, col;
	private boolean empty, red, finalized;
	private Color color;

	public Marker(int row, int col) {
		this.row = row;
		this.col = col;
		empty = true;
		red = true;
		finalized = false;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void fill() {
		empty = false;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setYellow() {
		red = false;
	}
	public boolean isRed() {
		return red;
	}
	public void finalize() {
		finalized = true;
	}
	public boolean isFinal() {
		return finalized;
	}
	public Color recolor() {
		if(empty) {
			color = new Color(255, 255, 255);
		} else if (red) {
			color = new Color(255, 70, 25);
		} else {
			color = new Color(255, 200, 25);
		}
		return color;
	}
}