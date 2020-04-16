public class Logic {

	public State state;
	private boolean gameOver;
	
	public Logic(State state) {
		this.state = state;
		gameOver = false;
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
			state.removeFilledRows();
			state.launchNewFigure();
			if (!state.isFigureFitTheField()) {
				this.gameOver = true;
			}
		}
		return true;
	}

	public void dropDown() {
		while (state.isFigureFitTheField()) {
			state.row++;
		}
		state.row--;
	}
	
	public void rotate() {
		Figure rotatedFigure = new Figure(state.figure.rotatedFigureData());
		Figure oldFigure = state.figure;
		state.figure = rotatedFigure;
		if (!state.isFigureFitTheField()) {
			state.figure = oldFigure;
		}
	}
	
	public boolean gameOver() {
		return gameOver;
	}

}