package columns;

public class State1 {
	
	static final int ROWS = 15;
	static final int COLUMNS = 7;
	
	public Field1 field;
	Figure1 figure;
	public int row;
	public int col;
	
	public State1() {
		this.field = new Field1(COLUMNS, ROWS);
		launchNewFigure();
	}
	
	void launchNewFigure() {
		this.figure = new Figure1();
		row = 0;
		col = field.data[0].length / 2 - figure.data.length / 2;
	}
	
	boolean isFigureFitTheField() {
		int height = field.data.length;
		int width = field.data[0].length;
		for (int r = 0; r < figure.data.length; r++) {
			for (int c = 0; c < 1; c++) {
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
	
}
