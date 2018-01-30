import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Color;

public class Layout {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 135, 180));

		//(width, height)
		frame.setSize(700, 700);
		frame.setTitle("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board b = new Board();
		b.createGrid();

		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if(!b.endGame()) {
					boolean addOne = false;
					int x = event.getX()/(20+b.getCell());
					if(x < b.getWidth()/100) {
						for(int i = b.getRow()-1; i >= 0; i--) {
							if(b.getMarker(i, x).isEmpty()) {
								b.getMarker(i, x).fill();
								addOne = true;
								break;
							}
						}
					}
					if(addOne) {
						b.repaint();
					}
				}
			}
		});

		frame.add(b);
		frame.setVisible(true);
	}
}