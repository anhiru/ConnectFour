import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Color;
//import javax.swing.JTextField;

public class ConnectFour {
	private static Board cf;
	private static JFrame f;
	private static JButton b;

	public static void main(String[] args) {
		f = new JFrame();

		//(width, height)
		f.setSize(700, 700);
		f.setTitle("Connect Four");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setBackground(new Color(0, 135, 180));
		
		cf = new Board();
		cf.createGrid();

		//final JTextField tf = new JTextField();
		//tf.setBounds(330, 20, 150, 20);
		b = new JButton("REPLAY");
		b.setBounds(300, 40, 95, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.newGame();
			}
		});
		f.add(b);
		//f.add(tf);

		f.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(!cf.endGame()) {
					boolean addOne = false;
					int x = e.getX()/(20+cf.getCell());
					if(x < cf.getWidth()/100) {
						for(int i = cf.getRow()-1; i >= 0; i--) {
							if(cf.getMarker(i, x).isEmpty()) {
								cf.getMarker(i, x).fill();
								addOne = true;
								break;
							}
						}
					}
					if(addOne) {
						cf.repaint();
					}
				}
			}
		});

		f.add(cf);
		f.setVisible(true);
	}
}