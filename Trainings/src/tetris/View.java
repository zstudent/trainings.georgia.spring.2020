package tetris;

public class View {
	private PlatformGraphics graphics;

	public View() {
		graphics = (color, row, col) -> {
		};
	}

	public View(PlatformGraphics graphics) {
		this.graphics = graphics;
	}

	public void draw(State state) {
		drawData(state.field.data, 0, 0, true);
		drawData(state.figure.data, state.row, state.col, false);
	}

	private void drawData(int[][] data, int row, int col, boolean drawBlacks) {
		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				int color = data[r][c];
				if (color == 0 && !drawBlacks)
					continue;
				drawBox(color, row + r, col + c);
			}
		}
	}

	private void drawBox(int color, int row, int col) {
		graphics.fillRect(color, row, col);
	}
}
