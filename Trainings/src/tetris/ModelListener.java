package tetris;

public interface ModelListener {
	void fireGameOver();
	void onChange(State state);
	void fireScoreChange(int numClearedRows);
}
