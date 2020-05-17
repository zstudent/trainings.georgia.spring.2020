package snake;

import java.util.ArrayList;
import java.util.List;

public class Model {
	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<>();

	public void moveLeft() {
		logic.moveLeft();
		fireOnChange();

	}

	public void moveRight() {
		logic.moveRight();
		fireOnChange();

	}

	public void moveUp() {
		logic.moveUp();
		fireOnChange();
	}

	public void moveDown() {
		logic.moveDown();
		fireOnChange();
	}

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}
	}

	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}

	public boolean gameOver() {
		return logic.gameOver();
	}

	public void moving() {
		logic.moving();
		if (!logic.gameOver())
			fireOnChange();

	}
}
