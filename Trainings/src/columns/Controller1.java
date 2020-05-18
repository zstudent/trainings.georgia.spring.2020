package columns;


public class Controller1 implements ModelListener1 {
	
	View1 view;
	Model1 model;
	
	public void set(Model1 model, View1 view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
	}

	@Override
	public void onChange(State1 state) {
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
}
