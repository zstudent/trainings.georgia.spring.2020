package tetris;

public class State {

	static final int ROWS = 20;
	static final int COLUMNS = 10;

	public Field field;
	Figure figure;
	public int row;
	public int col;
	int[][] backup ;
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
				field.data[row + r][col + c] = v;
			}
		}
	}

	void rotateFigureLeft()
	{
		int[][] rotatedFigure = new int[figure.data.length][figure.data[0].length];
		int height = figure.data.length;
		int width = figure.data[0].length;

		for(int r=0; r<figure.data.length; r++)
		{
			for(int c=0; c<figure.data[r].length; c++)
			{
				rotatedFigure[width-c-1][height - r - 1] = figure.data[r][c];//Should inverse the matrix.
			}
		}
		backup = figure.data;
		figure.data = rotatedFigure;
		mirror();
	}

	private void mirror()
	{
//		int width = figure.data[0].length;
//		int height = figure.data.length;
//		for(int row=0; row<height; row++)
//		{
//			for(int col =0; col<width; col++){
//				int temp = figure.data[row][col];
//				figure.data[row][col] = figure.data[row][figure.data[row].length-col-1];
//				figure.data[row][figure.data[row].length-col-1] = temp;
//			}
//		}
	}

	public void undo()
	{
		figure.data = backup;
	}
}
