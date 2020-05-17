package snake;

public class Logic {
	State state;

	public Logic(State state) {
		this.state = state;
	}

	public void moveLeft() {
		if (state.move != 1)
			state.move = 3;
	}

	public void moveRight() {
		if (state.move != 3)
			state.move = 1;
	}

	public void moveUp() {
		if (state.move != 2)
			state.move = 0;
	}

	public void moveDown() {
		if (state.move != 0)
			state.move = 2;
	}

	public boolean gameOver() {
		return state.gameOver();
	}

	public void moving() {
		state.moving();
	}
}
