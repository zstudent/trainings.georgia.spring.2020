package columns;

public class View1 {
	
	private PlatformGraphics1 graphics;
	
	public View1() {
		graphics = (color, row, col) -> {};
	}
	
	public View1(PlatformGraphics1 graphics) {
		this.graphics = graphics;
	}
	
	public void draw(State1 state) {
		drawData(state.field.data, 0, 0, true);
		drawData(state.figure.data, state.row, state.col, false);
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
