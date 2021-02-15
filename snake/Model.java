package snake;

import java.util.ArrayList;
import java.util.List;

import snake.Logic;
import snake.ModelListener;
import snake.State;

public class Model {
	Logic logic = new Logic(new State());
	ArrayList <ModelListener> listeners = new ArrayList <ModelListener>();

	public void move() {
		logic.moving();
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

	public void moveLeft() {
		logic.moveLeft();
		fireOnChange();
	}

	public void moveRight() {
		logic.moveRight();
		fireOnChange();
	}

	public void moveDown() {
		logic.moveDown();
		fireOnChange();
	}

	public void moveUp() {
		logic.moveUp();
		fireOnChange();
	}
}
