package tetris;

public interface PlatformGraphics {

	void fillRect(int color, int row, int col);
	
	void drawScore(int score, int row, int col);
	
	void drawLevel(int level, int row, int col);
	
}
