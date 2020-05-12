package columns;

import java.util.ArrayList;
import java.util.List;

public class Model {

	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<ModelListener>();
	
	public void addListener(ModelListener listener) {
		listeners.add(listener);
	}
	
	public void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}
	}
	
	public void switchCubeDown() {
		logic.switchCubeDown();
		fireOnChange();
	}
	
	public void switchCubeUp() {
		logic.switchCubeUp();
		fireOnChange();
	}
	
	public void moveColumn() {
		logic.moveColumn();
		fireOnChange();
	}
	
	public void moveColumnRight() {
		logic.moveColumnRight();
		fireOnChange();
	}
	
	public void moveColumnLeft() {
		logic.moveColumnLeft();
		fireOnChange();
	}
		
	public void dropColumn() {
		logic.dropColumn();
		fireOnChange();
	}
	
	public void packField() {
		logic.packField();
		fireOnChange();
	}
	
	public void pasteFigure() {
		logic.pasteFigure();
		fireOnChange();
	}
	
	public void testField() {
		logic.testField();
		fireOnChange();
	}
	
	public void removeNeighbours() {
		logic.removeNeighbours();
		fireOnChange();
	}
	
	public boolean noChange() {
		return logic.state.NoChanges;
	}
	
	public boolean isFigureFitTheField() {
		return logic.isFigureFitTheField();
	}
	
	public boolean isFieldFull() {
		return logic.isFieldFull();
	}
	
	public void increaseLevel() {
		logic.increaseLevel();
		fireOnChange();
	}
	
	public void decreaseLevel() {
		logic.decreaseLevel();
		fireOnChange();
	}
	
	public void pauseGame() {
		logic.pauseGame();
	}
	
	public boolean isPaused() {
		return logic.state.paused;
	}
	
	public long gameSpeed() {
		return logic.gameSpeed();
	}
}
