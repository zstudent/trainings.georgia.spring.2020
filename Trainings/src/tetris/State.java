package tetris;

public class State {
	static final int ROWS = 20;
	static final int COLUMNS = 10;

	public Field field;
	Figure figure;
	public int row;
	public int col;

	public State() {
		this.field = new Field(COLUMNS, ROWS);
		launchNewFigure();
	}

	void launchNewFigure() {
		this.figure = new Figure();
		row = 0;
		col = field.data[0].length / 2 - figure.data[0].length / 2;
	}

	boolean isFigureFitTheField() {
		int height = field.data.length;
		int width = field.data[0].length;
		for (int r = 0; r < figure.data.length; r++) {
			for (int c = 0; c < figure.data[r].length; c++) {
				if (figure.data[r][c] == 0)
					continue;
				int figureBoxRowOnTheField = row + r;
				int figureBoxColOnTheField = col + c;
				if (figureBoxRowOnTheField < 0 || figureBoxRowOnTheField >= height || figureBoxColOnTheField < 0
						|| figureBoxColOnTheField >= width)
					return false;
				if (0 < field.data[figureBoxRowOnTheField][figureBoxColOnTheField])
					return false;
			}
		}
		return true;
	}

	void pasteFigureIntoTheField() {
		for (int r = 0; r < figure.data.length; r++) {
			for (int c = 0; c < figure.data[r].length; c++) {
				int v = figure.data[r][c];
				if (v == 0)
					continue;
				field.data[row + r][col + c] = v;
			}
		}

	}
}
