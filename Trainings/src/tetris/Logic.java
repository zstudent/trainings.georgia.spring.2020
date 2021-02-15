package tetris;

public class Logic {
	public State state;
	private boolean gameOver;

	public Logic(State state) {
		this.state = state;
		gameOver = false;
	}

	public boolean moveLeft() {
		if (!gameOver) {
			state.col--;
			if (!state.isFigureFitTheField()) {
				state.col++;
				return false;
			}
			return true;
		}
		return true;
	}

	public boolean moveRight() {
		if (!gameOver) {
			state.col++;
			if (!state.isFigureFitTheField()) {
				state.col--;
				return false;
			}
			return true;
		}
		return true;
	}

	public boolean moveDown() {
		if (!gameOver()) {
			state.row++;
			if (!state.isFigureFitTheField()) {
				state.row--;
				state.pasteFigureIntoTheField();
				state.field.removeFilledRows();
				state.launchNewFigure();
				if (!state.isFigureFitTheField()) {
					this.gameOver = true;
				}
			}
			Field.score += 1;
		}
		return true;
	}

	public void dropDown() {
		if (!gameOver()) {
			while (state.isFigureFitTheField()) {
				state.row++;
			}
			state.row--;
			if (!gameOver)
				Field.score += 20;
		}
	}

	public void rotateLeft() {
		if (!gameOver()) {
			Figure oldFigure = new Figure(state.figure.data);
			Figure newFigure = new Figure(state.figure.rotateLeft());

			state.figure = newFigure;
			if (!state.isFigureFitTheField()) {
				state.figure = oldFigure;
			}
		}

	}

	public void rotateRight() {
		if (!gameOver()) {
			Figure oldFigure = new Figure(state.figure.data);
			Figure newFigure = new Figure(state.figure.rotateRight());

			state.figure = newFigure;
			if (!state.isFigureFitTheField()) {
				state.figure = oldFigure;
			}
		}

	}

	public boolean gameOver() {
		return gameOver;
	}
}
