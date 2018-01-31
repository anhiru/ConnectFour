public class Player {
	//private String name;
	private int score;

	public Player() {
		//this.name = name;
		score = 0;
	}
	public void winOne() {
		score++;
	}
	public int getScore() {
		return score;
	}
}