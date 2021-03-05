

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
		if(rowsCleared == 1){
			return 50*level;
		}else if(rowsCleared == 2){
			return 150*level;
		}else if(rowsCleared == 3){
			return 350*level;
		}else if(rowsCleared == 4){
			return 1000*level;
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
