package tetris;

public class Controller implements ModelListener {

	View view;
	Model model;
	static int sumScore = 0;
	static boolean GameOver = false;
	static int sleepTime = 4000;
	static int sleepTimeLimit = 500;
	static int sleepTimeSpeed = 5;

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
		int checkScore = model.moveDown(); 
		sumScore+=checkScore;
		if(checkScore==-1) GameOver = true;
	}
	public void rotate() {
		model.rotate();
	}

	public void dropDown() {
		sumScore += model.dropDown();
	}
	public int SumScore() {
		return sumScore;
	}
	public boolean gameOver() {
		return GameOver;
	}
	public int SleepTime() {
		if(sumScore>sleepTimeLimit) {
			sleepTime = sleepTime/sleepTimeSpeed;
			sleepTimeLimit*=2;
			sleepTimeSpeed--;
		}
		return sleepTime;
	}

}
