package game.of.life;

public class Controller implements ModelListener {
	
	View view;
	Model model;

	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
	}
	
	@Override
	public void onChange(Model model) {
		view.draw(model.getState());
	}
	
}
