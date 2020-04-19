package tetris;

public class State {

	static final int ROWS = 20;
	static final int COLUMNS = 10;

	public Field field;
	Figure figure;
	public int row;
	public int col;
	int[][] backup ;	//Store backup for every rotation, so we can undo in case of bad placement.
	public int stateHeight =0 ;	//Store the height of state.
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
				if( ROWS - (row + r ) > stateHeight) stateHeight = ROWS - (row  + r);//Update stateHeight accordingly.
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
				rotatedFigure[r][c] = figure.data[r][c];
			}
		}
		for(int r=0; r<figure.data.length; r++)
		{
			for(int c=0; c<figure.data[r].length; c++)
			{
			 	figure.data[r][c] = rotatedFigure[c][3-r];
			}
		}
		figure.figureHeight = figure.calculateFigureHeight();
		backup = rotatedFigure;
	}

	public void undo()
	{
		figure.data = backup;
	}

	public boolean clearState()
	{
		field.data = new int[field.data.length][field.data[0].length];
		//launchNewFigure();
		return true;
	}
}
