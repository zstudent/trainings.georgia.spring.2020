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
			state.field.removeFilledRows(state);
			state.launchNewFigure();
			// TODO:  homework:  determine GAME OVER
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
		int[][] oldfirst = state.figure.data;
		state.figure.data = new Figure().rotate_map.get(state.figure.data);
		if(!state.isFigureFitTheField()) {
			state.figure.data = oldfirst;
		}
		
	}
	
	boolean gameOver() {
		if(state.isFigureFitTheField()) {
			return false;
		}
		return true;			
	}

}
