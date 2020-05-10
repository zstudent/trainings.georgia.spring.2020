package tetris;

public interface ModelListener {
	void fireGameOver(String msg);
	void onChange(State state);
	void fireScoreChange(int numClearedRows,boolean restart);
}
