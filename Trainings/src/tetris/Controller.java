package tetris;

public class Controller implements ModelListener {

	View view;
	Model model;
	boolean gameOver = false;
	int timer = 500;

	public void set(Model model, View view) {
		this.model = model;
		this.view = view;
		model.addListener(this);
	}

	@Override
	public void fireGameOver(String msg) {
		gameOver = true;
		view.drawGameOverLabel(msg);
	}

	public void restartGame()
	{
		model.restartGame();
		timer = 500;	//Restart timer.
		fireScoreChange(0,true);
		fireGameOver("");
		gameOver = false;
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

	public void moveLeft() { if(!gameOver) model.moveLeft(); }

	public void moveRight() { if(!gameOver) model.moveRight(); }

	public void moveDown() { if(!gameOver) model.moveDown(); }

	public void dropDown() { if(!gameOver) model.dropDown(); }

	public void rotateLeft() { if(!gameOver) model.rotateLeft();}

}
