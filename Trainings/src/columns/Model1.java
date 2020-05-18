package columns;

import java.util.ArrayList;
import java.util.List;

public class Model1 {
	
	Logic1 logic = new Logic1(new State1());
	List<ModelListener1> listeners = new ArrayList<>();
	
	public void moveLeft() {
		if (logic.moveLeft())
			fireOnChange();
	}

	private void fireOnChange() {
		for (ModelListener1 listener : listeners) {
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

	public void addListener(ModelListener1 modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}

	public void dropDown() {
		logic.dropDown();
		fireOnChange();
	}
	
}
