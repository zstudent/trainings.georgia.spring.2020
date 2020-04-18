package tetris;

public class Controller implements ModelListener {

	View view;
	Model model;
	GameProcessFIlter filter;
	int timer = 500;

	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
		filter = new GameProcessFIlter(model);
	}

	@Override
	public void fireGameOver() {
		filter.setGameOver(true);
		view.drawGameOverLabel("Game over");
	}

	public void restartGame()
	{
		model.restartGame();
		filter.setGameOver(false);
		timer = 500;	//Restart timer.
	}

	@Override
	public void onChange(State state) {
		view.draw(state);
	}

	//If rows were cleared update score on view and increase speed.
	@Override
	public void fireScoreChange(int numClearedRows) {
		increaseSpeed();
		view.drawScoreLabel(""+numClearedRows);
	}

	//Increase speed by not too much, So game will be playable.
	private void increaseSpeed()
	{
		timer=timer*4/5;
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
