package tetris;

@FunctionalInterface
public interface PlatformGraphics {
	void fillRect(int color, int row, int col);
}
