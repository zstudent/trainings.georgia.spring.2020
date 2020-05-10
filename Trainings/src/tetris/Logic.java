public class Logic {

	public static State state;
	public Logic(State state) {
		Logic.state = state;
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
			int count = state.field.removeFilledRows();
			state.rowsCleared+=count;
			state.score+=computeScore(count, state.level);
			if(state.rowsCleared >= 5){
				state.level++;
				state.rowsCleared-=5;
			}
			state.launchNewFigure();
			// TODO:  homework:  determine GAME OVER
			if(!state.isFigureFitTheField()){
				state.gameOver = true;
				return true;
			}
			return true;
		}
		return true;
	}

	private int computeScore(int rowsCleared, int level){
		for (int i=1; i<=100; i++) {
			int count=0;
			if (rowsCleared==i) {
				Tetris.playSound("spa.wav");
				return (count+=50)*level;
			}
			}
			
		return 0;
	}

	public void dropDown() {
		while (state.isFigureFitTheField()) {
			state.row++;
		}
		state.row--;
	}

	public boolean rotate() {
		state.rotateFigure();
		if (!state.isFigureFitTheField()) {
			state.figure.data = state.backup;
			return false;
		}
		return true;
	}

}
