import java.util.ArrayList;

public class Model {
	
	Logic logic = new Logic(new State());
	ArrayList <ModelListener> listeners = new ArrayList <ModelListener>();
	
	public void move() {
		logic.move();
		if (!logic.gameOver())
			fireOnChange();
	}
	
	private void fireOnChange() {
		for (ModelListener listener : listeners)
			listener.onChange(logic.state);
	}
	
	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}
	
	public void restart() {
		logic = new Logic(new State());
		fireOnChange();
	}
	
	public void refreshView() {
		fireOnChange();
	}

	public boolean gameOver() {
		return logic.gameOver();
	}
	
	public void turnLeft() {
		logic.turnLeft();
		fireOnChange();
	}
	
	public void turnRight() {
		logic.turnRight();
		fireOnChange();
	}
	
	public void turnDown() {
		logic.turnDown();
		fireOnChange();
	}
	
	public void turnUp() {
		logic.turnUp();
		fireOnChange();
	}
}
