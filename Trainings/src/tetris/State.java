package tetris;

public class State {

	private static final int ROWS = 20;
	private static final int COLUMNS = 10;
	
	Field field;
	Figure figure;
	public int row;
	public int col;

	public State() {
		this.field = new Field(COLUMNS, ROWS);
		this.figure = new Figure();
		row = 0;
		col = field.data[0].length/2 - figure.data[0].length/2;
	}

	public boolean isFigureFitTheField() {
		int height = field.data.length;
		int width = field.data[0].length;
		for (int r = 0; r < figure.data.length; r++) {
			for (int c = 0; c < figure.data[r].length; c++) {
				if (figure.data[r][c] == 0) continue;
				int fr = row + r;
				int fc = col + c;
				if (fr < 0 || fr >= height || fc < 0 || fc >= width)
					return false;
			}
		}
		return true;
	}

}
