import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.*;

public class Layout {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		//(width, height)
		frame.setSize(700, 620);
		frame.setTitle("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board b = new Board();
		frame.add(b);
		frame.setVisible(true);

		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int x = event.getX(); //returns x coordinate of mouse click location
				int y = event.getY(); //returns y coordinate of mouse click location
				b.moveTo(x, y);
			}
		});
	}
}