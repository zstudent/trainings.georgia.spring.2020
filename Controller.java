public class Controller implements ModelListener {

	View view;
	Model model;

	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
	}

	@Override
	public void onChange(State state) {
		view.draw(state);
	}

	public void moveLeft() {
		model.moveLeft();
	}

	public void moveRight() {
		model.moveRight();
	}

	public void moveDown() {
		model.moveDown();
	}

	public void dropDown() {
		model.dropDown();
	}
	
	public void rotate() {
		model.rotate();
	}
	
	public boolean gameOver() {
		return model.gameOver();
	}
	
	public void restart() {
		model.restart();
	}
	

}