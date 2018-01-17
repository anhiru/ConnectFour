import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Board {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		//(width, height)
		frame.setSize(700, 600);
		frame.setTitle("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Draw d = new Draw();
		frame.add(d);
		frame.setVisible(true);
	}
}

class Draw extends JComponent {
	private int row, col, cell, padding;
	//private int[][] board;
	public Draw() {
		row = 600;
		col = 700;
		cell = 90;
		padding = 5;
		//board = new int[row][column];
	}
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		//(left, top, width, height)
		Rectangle box = new Rectangle(0, 0, col, row);
		g.setColor(new Color(66, 134, 244));
		g.draw(box);
		g.fill(box);

		for(int i = 0; i < row; i = i + cell + 2*padding) {
			for(int j = 0; j < col; j = j + cell + 2*padding) {
				Ellipse2D.Double circle = new Ellipse2D.Double(j + padding, i + padding, cell, cell);
				g.setColor(Color.WHITE);
				g.draw(circle);
				g.fill(circle);
				//if(board[i][j] > 0) {} 
			}
		}
	}
}

class CircleComponent extends RectangleComponent {

}