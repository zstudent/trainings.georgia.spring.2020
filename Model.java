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
	
	public void rotate() {
		if(logic.rotate())
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
		return logic.gameOver;
	}
	
	public int countFigures() {
		return logic.countFigures();
	}
	
	public int getScore() {
		return logic.getScore();
	}
	
	public int linesCleared() {
		return logic.linesCleared();
	}
	
	public int getLevel() {
		return logic.getLevel();
	}
	
}
