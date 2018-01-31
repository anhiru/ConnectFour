public class Player {
	//private String name;
	private int wins;

	public Player() {
		//this.name = name;
		wins = 0;
	}
	public void winOne() {
		wins++;
	}
	public int getWins() {
		return wins;
	}
}