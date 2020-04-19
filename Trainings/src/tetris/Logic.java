package tetris;

public class Logic {

	public State state;

	public Logic(State state) {
		this.state = state;
	}

	public boolean moveLeft() {
		state.col--;
		if (!state.isFigureFitTheField()) {
			state.col++;
			return false;
		}
		return true;
	}

	public boolean moveRight() {
		state.col++;
		if (!state.isFigureFitTheField()) {
			state.col--;
			return false;
		}
		return true;
	}

	//Changed moveDown()'s return type to int instead of boolean,
	//It now returns  :-1 if no rows cleared and game goes on,
	//				  :-2 if no rows cleared and game is over after moving down
	//				  :else if number of rows cleared
	public int moveDown() {
		state.row++;
		if (!state.isFigureFitTheField()) {
			state.row--;
			state.pasteFigureIntoTheField();
			int clearedRows = state.field.removeFilledRows();
			if(clearedRows>0) return clearedRows;
			state.launchNewFigure();
			if(state.stateHeight >= state.ROWS - state.figure.figureHeight) return -1;
			return 0;
		}
		return 0;
	}

	public boolean rotateLeft()
	{
		state.rotateFigureLeft();
		if(!state.isFigureFitTheField()){
			state.undo();
			return false;
		}
		return true;
	}

	public void dropDown() {
		while (state.isFigureFitTheField()) {
			state.row++;
		}
		state.row--;
	}

	public boolean clearState()
	{
		state.stateHeight = 0;
		state.launchNewFigure();
		return state.clearState();
	}
}
