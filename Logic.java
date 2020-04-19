public class Logic {

	public State state;
	public boolean gameOver = false;

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
			int clearedRows = state.field.removeFilledRows();
			if(clearedRows > 0) {
				updateScore(clearedRows);
				updateClearedLinesAndLevel(clearedRows);
			}
			state.launchNewFigure();
			if(!state.isFigureFitTheField()) gameOver = true;
			return true;
		}
		return true;
	}

	
	private void updateScore(int clearedRows) {
		switch (clearedRows) {
			case 1: state.score += 5;	break;
			case 2: state.score += 10;  break;
			case 3: state.score += 20;  break;
			case 4: state.score += 40; 	break;
			default: state.score += 50;
		}
	}
	
	
	private void updateClearedLinesAndLevel(int clearedRows) {
		for(int i=0; i<clearedRows; i++) {
			state.linesCleared++;
			if(state.linesCleared % 5 == 0) state.level++;
		}
	}
	
	public boolean rotate() {
		Figure currFig = new Figure(state.figure.data);
		int[][] currFigData = currFig.data;
		int[][] rotatedFigData = new int[currFigData.length][currFigData.length];
		for(int r=0; r<currFigData.length; r++) {
			for(int c=0; c<currFigData[r].length; c++) {
				rotatedFigData[currFigData.length - 1 - c][r] = currFigData[r][c];
			}
		}
		state.figure = new Figure(rotatedFigData);
		if(!state.isFigureFitTheField()) {
			state.figure = currFig;
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
	
	public int countFigures() {
		return state.countFigures;
	}
	
	public int getScore() {
		return state.score;
	}
	
	public int linesCleared() {
		return state.linesCleared;
	}
	
	public int getLevel() {
		return state.level;
	}

}
