import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.*;

class Marker extends JComponent {
	private int x, y;
	private Color color;
	private Ellipse2D.Double marker;
	private boolean empty;

	public Marker(int x, int y) {
		this.x = x;
		this.y = y;
		marker = new Ellipse2D.Double(x, y, 90, 90);
		empty = true;
	}
	public Ellipse2D.Double getMarker() {
		return marker;
	}
	public Color getColor(int i) {
		if(i == 1) {
			color = new Color(255, 70, 25);
		} else if (i == 2) {
			color = new Color(255, 200, 25);
		} else {
			color = new Color(255, 255, 255);
		}
		return color;
	}
	public int getStatus() {
		if(empty) {
			return 0;
		}
		return 1;
	}
}