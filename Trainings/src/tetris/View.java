package tetris;

public class View {

	private PlatformGraphics graphics;
	
	public View() {
		graphics = new PlatformGraphics() {
			
			@Override
			public void fillRect(int color, int row, int col) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drawScore(int score, int row, int col) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drawLevel(int level, int row, int col) {
				// TODO Auto-generated method stub
				
			}

		};
	}
	
	public View(PlatformGraphics graphics) {
		this.graphics = graphics;
	}

	public void draw(State state) {
		drawScore(state.score, 0, 0);
		drawLevel(state.level, 100, 0);
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
	
	private void drawScore(int score, int row, int col) {
		graphics.drawScore(score, row, col);
	}
	
	private void drawLevel(int level, int row, int col) {
		graphics.drawLevel(level, row, col);
	}
	

}
