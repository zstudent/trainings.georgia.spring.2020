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

	boolean isFigureFitTheField(Figure fig) {
		int height = field.data.length;
		int width = field.data[0].length;
		for (int r = 0; r < fig.data.length; r++) {
			for (int c = 0; c < fig.data[r].length; c++) {
				if (fig.data[r][c] == 0)
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
	int[][] rotate() {
		int[][] tempData = new int[this.figure.data.length][this.figure.data.length];
		Figure temp = new Figure(tempData);
		for(int r=0; r<temp.data.length; r++){
			for(int c=0; c<temp.data[r].length; c++){
				temp.data[r][c] = this.figure.data[c][3-r];
			}
		}
		return temp.data;
	}
	boolean fillData() {
		for(int i=0; i<field.data[0].length; i++) {
			if(field.data[0][i]!=0)
				return true;
		}
		for(int i=0; i<field.data[0].length; i++) {
			if(field.data[1][i]!=0)
				return true;
		}
		return false;
	}

}
