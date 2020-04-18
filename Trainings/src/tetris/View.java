package tetris;

import javax.swing.*;

public class View {

	private PlatformGraphics graphics;
	private PlatformLable gameOverLable;
	private PlatformLable scoreLable;

	public View() {
		graphics = (color, row, col) -> {};
	}
	
	public View(PlatformGraphics graphics,PlatformLable gameOverLable,PlatformLable scoreLabel) {
		this.graphics = graphics;
		this.gameOverLable = gameOverLable;
		this.scoreLable = scoreLabel;
	}

	public void draw(State state) {
		drawData(state.field.data, 0, 0, true);
		drawData(state.figure.data, state.row, state.col, false);
	}

	public void drawGameOverLabel(String msg)
	{
		gameOverLable.writeLable(msg);
	}

	public void drawScoreLabel(String msg){
		scoreLable.writeLable(msg);
	}

	private void drawData(int[][] data, int row, int col, boolean drawBlacks) {
		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				int color = data[r][c];
				if (color == 0 && !drawBlacks) continue;
				drawBox(color, row + r, col + c);
			}
		}
	}


	private void drawBox(int color, int row, int col) {
		graphics.fillRect(color, row, col);
	}

}
