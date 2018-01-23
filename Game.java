import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private ArrayList<Player> players;
	//private int[] turn;
	private int playerTurn;

	public Game() {
		players = new ArrayList<Player>();
		playerTurn = 0;
	}
	public int getTurn() {
		return playerTurn;
	}
}