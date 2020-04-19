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
	public void fireGameOver(String msg) {
		filter.setGameOver(true);
		view.drawGameOverLabel(msg);
	}

	public void restartGame()
	{
		model.restartGame();
		timer = 500;	//Restart timer.
		fireScoreChange(0,true);
		fireGameOver("");
		filter.setGameOver(false);
	}

	@Override
	public void onChange(State state) {
		view.draw(state);
	}

	//If rows were cleared update score on view and increase speed.
	@Override
	public void fireScoreChange(int numClearedRows,boolean restart) {
		increaseSpeed();
		view.drawScoreLabel(""+numClearedRows,restart);
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
