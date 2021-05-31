package tetris;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<>();
	
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
		if (logic.moveDown())
			fireOnChange();
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
	
	public boolean gameOver() {
		return logic.state.gameIsOver;
	}
	
	public void rotate() {
		logic.rotate();
		fireOnChange();
	}

	public String updateLevel() {
		return logic.updateLevel();
	}

	public String updateScore() {
		return logic.updateScore();
	}

	public void restart() {
		// TODO Auto-generated method stub
		logic = new Logic(new State());
	}
	
}
