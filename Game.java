import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.*;

public class Game {
	//private ArrayList<Player> players;
	//private int[] turn;
	private Player[] players;
	private int playerTurn;

	public Game() {
		//players = new ArrayList<Player>();
		players = new Player[2];
		playerTurn = 0;
	}
	public int getTurn() {
		return playerTurn;
	}
}