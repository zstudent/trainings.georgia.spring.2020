
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
	
	public void restart() {
		model.restart();
	}
	
	public void move() {
		model.move();
	}

	public boolean gameOver() {
		return model.gameOver();
	}

	public void turnLeft() {
		model.turnLeft();
	}
	
	public void turnRight() {
		model.turnRight();
	}
	
	public void turnDown() {
		model.turnDown();
	}
	
	public void turnUp() {
		model.turnUp();
	}
	
}
