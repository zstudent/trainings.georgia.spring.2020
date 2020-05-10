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

	public void moveRight() {
		if (logic.moveRight())
			fireOnChange();
	}

	//Changed moveDown()'s body
	public void moveDown() {
		int move = logic.moveDown();
		if (move==0)
			fireOnChange();
		else if(move==-1){
			fireGameOver();
		}
		else{
			fireScoreChange(move);
		}
	}

	//Tell controller that score should be updated on view.
	private void fireScoreChange(int numClearedRows)
	{
		for(ModelListener listener : listeners)
		{
			listener.fireScoreChange(numClearedRows,false);
		}

	}

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(logic.state);
		}
	}

	//Tell controller that game is over.
	private void fireGameOver()
	{
		for(ModelListener listener : listeners)
		{
			listener.fireGameOver("Game Over");
		}
	}

	//Tell controller to update view() to show rotated body.
	public void rotateLeft()
	{
		if(logic.rotateLeft())
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

	public void restartGame()
	{
		logic.clearState();
		fireOnChange();
	}
}
