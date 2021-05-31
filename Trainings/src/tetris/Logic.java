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
			// TODO:  homework:  determine GAME OVER
			return finishGame();
		}
		return true;
	}

	public void dropDown() {
		while (state.isFigureFitTheField()) {
			state.row++;
		}
		state.row--;
	}
		
	
	public boolean finishGame() {
		if(!state.isFigureFitTheField()){
			state.gameIsOver = true;
			return false;
		}
		return true;
	}
	
	
	public void rotate() {
		Figure rotatedFigure = new Figure(state.figure.mapRotation.get(state.figure.data));
		Figure backUp = state.figure;
		state.figure = rotatedFigure;
		if (!state.isFigureFitTheField()) {
			state.figure = backUp;
		}
	}
	

	public String updateLevel() {
		int ourLevel = state.field.level;
		ourLevel = state.field.countRemovedRows / 5;
		return Integer.toString(ourLevel);
	}

	public String updateScore() {
		int ourScore = state.field.countRemovedRows * 10; 
		return Integer.toString(ourScore);
	}

}
