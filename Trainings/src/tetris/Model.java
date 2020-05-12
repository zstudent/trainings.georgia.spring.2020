package tetris;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<>();
	
	public void moveLeft() {
		if (logic.moveLeft() && !logic.gameOver())
			fireOnChange();
	}

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}
	}

	public void moveRight() {
		if (logic.moveRight() && !logic.gameOver())
			fireOnChange();
	}
	
	public void moveDown() {
		if (logic.moveDown() && !logic.gameOver())
			fireOnChange();
	}

	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}

	public void dropDown() {
		if(!logic.gameOver()) {
			logic.dropDown();			
			fireOnChange();
		}
	}

	public void rotate() {
		if(!logic.gameOver()) {
			logic.rotate();
			fireOnChange();			
		}
		
	}

	public int level() {
		return logic.state.level;
	}

	public void playAgain() {
		logic = new Logic(new State());
		fireOnChange();
	}
	
	
	
}
