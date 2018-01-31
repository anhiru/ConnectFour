import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.util.ArrayList;

class Board extends JComponent {
	private int row, col, cell, height, width;
	private boolean redTurn, gameOver;
	private Marker[][] grid;

	public Board() {
		row = 6;
		col = 7;
		cell = 80;
		height = (20+cell)*row;
		width = (20+cell)*col;
		redTurn = false;
		gameOver = false;
		grid = new Marker[row][col];
	}
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setFont(new Font("Impact", Font.PLAIN, 24));
		g.drawString("Player 1", 20, 40);
		g.drawString("Player 2", width-85, 40);

		//(left, top, width, height)
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Marker m = grid[i][j];
				if(m.isEmpty()) {
					g.setColor(m.recolor());
				} else if(!m.isFinal()) {
					if(redTurn) {
						m.setRed();
					} else {
						m.setYellow();
					}
					g.setColor(m.recolor());
					m.finalize();
					checkWin(m);
					if(gameOver) {
						g.drawString("GAME!", width/2-25, 30);
					}
				} else {
					g.setColor(m.recolor());
				}
				Ellipse2D.Double circle = new Ellipse2D.Double(j*(cell+20)+cell/20+5, i*(cell+20)+cell/20+85, cell, cell);
				g.draw(circle);
				g.fill(circle);
			}
		}
		redTurn = !redTurn;
	}
	public void createGrid() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j] = new Marker(i, j);
			}
		}
	}
	public int getRow() {
		return row;
	}
	public int getCell() {
		return cell;
	}
	public int getWidth() {
		return width;
	}
	public Marker getMarker(int row, int col) {
		return grid[row][col];
	}
	public boolean endGame() {
		return gameOver;
	}
	public void checkWin(Marker m) {
		int i = m.getRow();
		int j = m.getCol();
		ArrayList<Marker> temp = new ArrayList<Marker>();
		
		//win vertically
		for(int k = i; k < row; k++) {
			temp.add(grid[k][j]);
		}
		isFourInARow(temp);

		//win horizontally
		for(int k = 0; k < col; k++) {
			temp.add(grid[i][k]);
		}
		isFourInARow(temp);

		//win diagonally up left
		int dRow = i;
		int dCol = j;
		while(dRow < row && dCol < col) {
			dRow++;
			dCol++;
		}
		while(dRow >= 0 && dCol >= 0) {
			temp.add(grid[dRow][dCol]);
			dRow--;
			dCol--;
		}
		isFourInARow(temp);

		//win diagonally up right
		dRow = i;
		dCol = j;
		while(dRow < row && dCol >= 0) {
			dRow++;
			dCol--;
		}
		while(dRow >= 0 && dCol < col) {
			temp.add(grid[dRow][dCol]);
			dRow--;
			dCol++;
		}
		isFourInARow(temp);
	}
	//checks arraylist for 4 in a row
	public void isFourInARow(ArrayList<Marker> temp) {
		if(temp.size() >= 4) {
			int count = 1;
			for(int i = 0; i < temp.size() - 1; i++) {
				Marker current = temp.get(i);
				Marker next = temp.get(i+1);
				if(!current.isEmpty() && !next.isEmpty() && current.isRed() == next.isRed()) {
					count++;
				} else {
					count = 1;
				}
				if(count == 4) {
					gameOver = true;
				}
			}
		}
		temp.clear();
	}
	//reset default conditions
	public void newGame() {
		gameOver = false;
		redTurn = true;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j].reset();
			}
		}
		repaint();
	}
}