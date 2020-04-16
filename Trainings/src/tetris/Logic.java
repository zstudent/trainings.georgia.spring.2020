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

	public boolean moveDown() {
		state.row++;
		if (!state.isFigureFitTheField()) {
			state.row--;
			state.pasteFigureIntoTheField();
			state.field.removeFilledRows();
			state.launchNewFigure();
			if(state.stateHeight >= state.ROWS) return false;
			return true;
		}
		return true;
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
}
