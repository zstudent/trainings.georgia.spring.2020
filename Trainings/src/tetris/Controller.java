package tetris;

public class Controller implements ModelListener {

	View view;
	Model model;
	GameOverFIlter filter;

	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
		filter = new GameOverFIlter(model);
	}

	@Override
	public void fireGameOver() {
		filter.setGameOver(true);
	}

	@Override
	public void onChange(State state) {
		view.draw(state);
	}

	public void moveLeft() { filter.moveLeft(); }

	public void moveRight() {
		filter.moveRight();
	}

	public void moveDown() {
		filter.moveDown();
	}

	public void dropDown() { filter.dropDown(); }

	public void rotateLeft() { filter.rotateLeft();}
}
