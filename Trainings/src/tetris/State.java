package tetris;

import java.util.Arrays;

public class State {

	static final int ROWS = 20;
	static final int COLUMNS = 10;

	Field field;
	Figure figure;
	public int row;
	public int col;

	public State() {
		this.field = new Field(COLUMNS, ROWS);
		launchNewFigure();
	}

	void launchNewFigure() {
		this.figure = new Figure(Figure.O);
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
				int figureBoxRowOnTHeField = row + r;
				int figureBoxColOntTheField = col + c;
				if (figureBoxRowOnTHeField < 0
						|| figureBoxRowOnTHeField >= height
						|| figureBoxColOntTheField < 0
						|| figureBoxColOntTheField >= width)
					return false;
				if (0 < field.data[figureBoxRowOnTHeField][figureBoxColOntTheField])
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

	public void removeFilledRows() {
		OUTER: for (int r = 0; r < field.data.length; r++) {
			for (int c = 0; c < field.data[r].length; c++) {
				if (field.data[r][c] == 0)
					continue OUTER;
			}
			for (int i = r; i > 0; i--) {
				System.arraycopy(field.data[i - 1], 0, field.data[i], 0,
						field.data[i].length);
			}
			Arrays.fill(field.data[0], 0);
		}
	}

}
