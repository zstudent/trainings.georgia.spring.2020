import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Model {
	Logic logic = new Logic(new State());
	List<ModelListener> listeners = new ArrayList<>();

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}
	}
		public void moveRight() {
		if (logic.moveRight()) {
			fireOnChange();
		}
	}
		public void moveLeft() {
		if (logic.moveLeft()) {
			fireOnChange();
		}
	}
		public void moveDown() {
		if (logic.moveDown()){
			fireOnChange();
		}
	}

	public void dropDown() {
		logic.dropDown();
		fireOnChange();
	}

	public void rotate(){
		if(logic.rotate()){
			fireOnChange();
		}
	}
	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}
}