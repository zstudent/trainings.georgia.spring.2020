package tetris;

import java.util.ArrayList;
import java.util.List;

public class Model {

	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<>();
	public int score;

	public void moveLeft() {
		if (logic.moveLeft())
			fireOnChange();
	}

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}

	}

	public void moveRight() {
		if (logic.moveRight())
			fireOnChange();
	}

	public void moveDown() {
		if (logic.moveDown()) {
			fireOnChange();
		}
	}

	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();

	}

	public void dropDown() {
		logic.dropDown();
		fireOnChange();

	}

	public void rotateLeft() {
		logic.rotateLeft();
		fireOnChange();

	}

	public void rotateRight() {
		logic.rotateRight();
		fireOnChange();

	}

	public boolean gameOver() {
		return logic.gameOver();
	}

}
