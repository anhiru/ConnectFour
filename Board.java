import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.util.ArrayList;

class Board extends JComponent {
	private int row, col, cell, height, width; //playerOneWins, playerTwoWins;
	private boolean redTurn, gameOver;
	private Marker[][] grid;
	private ArrayList<Marker> test;

	public Board() {
		//playerOneWins = 0;
		//playerTwoWins = 0;
		row = 6;
		col = 7;
		cell = 80;
		height = (20+cell)*row;
		width = (20+cell)*col;
		redTurn = true;
		gameOver = false;
		grid = new Marker[row][col];
		test = new ArrayList<Marker>();
	}
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setFont(new Font("Impact", Font.PLAIN, 24));

		if(redTurn) {
			g.setColor(new Color(255, 70, 25));
			g.drawString("Player 1", 20, 40);
			g.setColor(Color.BLACK);
			g.drawString("Player 2", width-95, 40);
		} else {
			g.setColor(Color.BLACK);
			g.drawString("Player 1", 20, 40);
			g.setColor(new Color(255, 200, 25));
			g.drawString("Player 2",width-95, 40);
		}
		//g.drawString("Player 1", 20, 40);
		//g.drawString("Player 2", width-95, 40);

		//(left, top, width, height)
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Marker m = grid[i][j];
				if(m.isEmpty()) {
					g.setColor(m.recolor());
				} else if(!m.isFinal()) {
					if(redTurn) {
						m.setYellow();
					}
					g.setColor(m.recolor());
					m.finalize();
					checkWin(m);
					/*if(gameOver) {
						g.drawString("GAME!", width/2-30, 30);
					}*/
					if(gameOver) {
						//incrementWins(redTurn);
						if(!redTurn) {
							g.drawString("Player 1 wins!", width/2-70, 30);
						} else {
							g.drawString("Player 2 wins!", width/2-70, 30);
						}
					/*
					} else {
						g.setColor(Color.WHITE);
						g.drawString(Integer.toString(playerOneWins) + " — " + Integer.toString(playerTwoWins), width/2-30, 30);
					*/
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
		/*
		if(gameOver) {
			//incrementWins(redTurn);
			if(!redTurn) {
				g.drawString("Player 2 wins!", width/2-70, 30);
			} else {
				g.drawString("Player 1 wins!", width/2-70, 30);
			}
		/*
		} else {
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(playerOneWins) + " — " + Integer.toString(playerTwoWins), width/2-30, 30);
		}	
		*/
	}
	public void createGrid() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j] = new Marker(i, j);
			}
		}
	}
	/*
	public void setPlayers() {
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player();
		}
	}
	*/
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
		//ArrayList<Marker> temp = new ArrayList<Marker>();
		
		//win vertically
		for(int k = i; k < row; k++) {
			test.add(grid[k][j]);
		}
		isFourInARow(test);

		//win horizontally
		for(int k = 0; k < col; k++) {
			test.add(grid[i][k]);
		}
		isFourInARow(test);

		//win diagonally up left
		int dRow = i;
		int dCol = j;
		while(dRow < row-1 && dCol < col-1) {
			dRow++;
			dCol++;
		}
		while(dRow >= 0 && dCol >= 0) {
			test.add(grid[dRow][dCol]);
			dRow--;
			dCol--;
		}
		isFourInARow(test);

		//win diagonally up right
		dRow = i;
		dCol = j;
		while(dRow < row-1 && dCol > 0) {
			dRow++;
			dCol--;
		}
		while(dRow >= 0 && dCol < col) {
			test.add(grid[dRow][dCol]);
			dRow--;
			dCol++;
		}
		isFourInARow(test);
	}
	//checks arraylist for 4 in a row
	public void isFourInARow(ArrayList<Marker> temp) {
		if(test.size() >= 4) {
			int count = 1;
			for(int i = 0; i < test.size() - 1; i++) {
				Marker current = test.get(i);
				Marker next = test.get(i+1);
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
		test.clear();
	}
	/*
	public void incrementWins(boolean redWins) {
		if(redWins) {
			playerOneWins++;
		} else {
			playerTwoWins++;
		}
	}
	*/
	//reset default conditions
	public void newGame() {
		gameOver = false;
		redTurn = false;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j].reset();
			}
		}
		repaint();
	}
}