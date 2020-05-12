package columns;

public class Controller implements ModelListener{
	
	Model model;
	View view;
	
	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
	}

	@Override
	public void onChange(State state) {
		view.DrawField(state.newField);
		view.DrawFigure(state.fig);
		view.ShowLevel(state.Level);
		view.ShowScore(state.Score);
	}
	
	public void switchCubeDown() {
		model.switchCubeDown();
	}
	
	public void switchCubeUp() {
		model.switchCubeUp();
	}

	
	public void moveColumn() {
		model.moveColumn();
	}
	
	public void moveColumnRight() {
		model.moveColumnRight();
	}
	
	public void moveColumnLeft() {
		model.moveColumnLeft();
	}
	
	public void dropColumn() {
		model.dropColumn();
	}
	
	public void packField() {
		model.packField();
	}
	
	public void pasteFigure() {
		model.pasteFigure();
	}
	
	public void testField() {
		model.testField();
	}
	
	public boolean noChanges() {
		return model.noChange();
	}
	
	public boolean isFigureFitTheField() {
		return model.isFigureFitTheField();
	}
	
	public void removeNeighbours() {
		model.removeNeighbours();
	}
	
	public boolean isFieldFull() {
		return model.isFieldFull();
	}

	public void increaseLevel() {
		model.increaseLevel();
	}
	
	public void decreaseLevel() {
		model.decreaseLevel();
	}
	
	public void pauseGame() {
		model.pauseGame();
	}
	
	public boolean isPaused() {
		return model.isPaused();
	}
	
	public long gameSpeed() {
		return model.gameSpeed();
	}
}
