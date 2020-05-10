public class State {

	static final int ROWS = 20;
	static final int COLUMNS = 10;

	public int row;
	public int col;
	public int score;
	public int level = 1;
	public boolean gameOver = false;
	public int rowsCleared;
	public int[][] backup;
	public Field field;
	Figure figure;

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
				if(field.data[row + r][col + c]==0){
					field.data[row + r][col + c] = v;
				}
			}
		}
	}

	void rotateFigure() {
		int size = figure.data.length;
		backup = new int[figure.data.length][figure.data[0].length];
		for(int i = 0; i < figure.data.length; i++){
			System.arraycopy(figure.data[i], 0, backup[i],0,figure.data[0].length);
		}
		for (int i = 0; i < size/2; i++) {
			for (int j = i; j < size-i-1; j++) {
				int temp = figure.data[i][j];
				figure.data[i][j] = figure.data[j][size-1-i];
				figure.data[j][size-1-i] = figure.data[size-1-i][size-1-j];
				figure.data[size-1-i][size-1-j] = figure.data[size-1-j][i];
				figure.data[size-1-j][i] = temp;
			}
		}
	}
}