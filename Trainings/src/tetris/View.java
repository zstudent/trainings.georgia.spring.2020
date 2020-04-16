package tetris;

import javax.swing.*;

public class View {

	private PlatformGraphics graphics;
	private PlatformLable textLable;

	public View() {
		graphics = (color, row, col) -> {};
	}
	
	public View(PlatformGraphics graphics,PlatformLable textLable) {
		this.graphics = graphics;
		this.textLable = textLable;
	}

	public void draw(State state) {
		drawData(state.field.data, 0, 0, true);
		drawData(state.figure.data, state.row, state.col, false);
	}

	public void drawLabel()
	{
		textLable.writeLable("Game over");
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
