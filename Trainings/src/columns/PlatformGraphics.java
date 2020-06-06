package columns;

public interface PlatformGraphics {
	void drawBox(int row, int col, int color);
	void showScore(Model model);
	void showLevel(Model model);
	void showHelp();
}
