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
	
	public int moveDown() {
		int check =logic.moveDown(); 
		if (check>=0) {
			fireOnChange();
			return check;
		}
		if(check==-1) return -1;
		return 0;
	}
	public void rotate() {
		if (logic.rotate())
			fireOnChange();
	}

	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}

	public int dropDown() {
		int tempScore = logic.dropDown();
		fireOnChange();
		return tempScore;
	}
	
}
