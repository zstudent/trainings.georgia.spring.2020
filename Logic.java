
public class Logic {
	
	public State state;
	
	public Logic(State state) {
		this.state = state;
	}
	
	public void move() {
		state.move();
	}
	
	public boolean gameOver() {
		return !state.isSnakeFitTheField();
	}
	
	public void turnLeft() {
		state.turnLeft();
	}
	
	public void turnRight() {
		state.turnRight();
	}
	
	public void turnDown() {
		state.turnDown();
	}
	
	public void turnUp() {
		state.turnUp();
	}
}
