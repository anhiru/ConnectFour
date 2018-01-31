import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Color;
//import javax.swing.JTextField;

public class ConnectFour {
	private static Board cf;
	private static JFrame f;
	private static JButton bRed, bYellow;

	public static void main(String[] args) {
		f = new JFrame();

		//(width, height)
		f.setSize(700, 700);
		f.setTitle("Andrew's Connect Four");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setBackground(new Color(0, 135, 180));
		
		cf = new Board();
		cf.createGrid();

		//final JTextField tf = new JTextField();
		//tf.setBounds(330, 20, 150, 20);
		bRed = new JButton("RED START");
		bRed.setBounds(225, 40, 120, 30);
		bRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.newGame('r');
			}
		});
		bYellow = new JButton("YELLOW START");
		bYellow.setBounds(350, 40, 120, 30);
		bYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.newGame('y');
			}
		});
		f.add(bRed);
		f.add(bYellow);
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