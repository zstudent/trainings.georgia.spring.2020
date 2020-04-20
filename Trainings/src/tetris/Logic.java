package tetris;

public class Logic {

	public State state;

	public Logic(State state) {
		this.state = state;
	}

	public boolean moveLeft() {
		state.col--;
		if (!state.isFigureFitTheField(state.figure)) {
			state.col++;
			return false;
		}
		return true;
	}

	public boolean moveRight() {
		state.col++;
		if (!state.isFigureFitTheField(state.figure)) {
			state.col--;
			return false;
		}
		return true;
	}

	public int moveDown() {
		state.row++;
		if (!state.isFigureFitTheField(state.figure)) {
			state.row--;
			state.pasteFigureIntoTheField();
			boolean removedRows = state.field.removeFilledRows();
			boolean GameOver = state.fillData();
			if(GameOver) {
				return -1;
			}
			state.launchNewFigure();
			if(removedRows) return 50;
			return 0;
		}
		return 1;
	}
	public boolean rotate() {
		int[][] tempData = state.rotate();
		Figure tempfig = new Figure(tempData);
		if(state.isFigureFitTheField(tempfig)) {
			state.figure = tempfig;
			return true;
		}
		return false;
		
	}

	public int dropDown() {
		int res = 0;
		while (state.isFigureFitTheField(state.figure)) {
			state.row++;
			res++;
		}
		state.row--;
		return res;
	}

}
