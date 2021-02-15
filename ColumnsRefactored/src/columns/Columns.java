package columns;

import java.applet.Applet;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

public class Columns extends Applet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int SL = 25;
	static final int HEIGHT = 15, WIDTH = 7;
	static final int MAX_LEVEL = 7;
	static final int TIME_SHIFT = 250, MIN_TIME_SHIFT = 200;
	static final int FIG_TO_DROP = 33;
	static final int LEFT_BORDER = 2, TOP_BORDER = 2;

	static final Color gameColors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow,
			Color.pink, Color.magenta, Color.black };
	
	
	private State state;
	private Paint paint;
	
	private int k, action;
	private long calculateTime;
	private boolean keyPressed;

	private Thread thread;


	void Delay(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {}
	}

	public void init() {
		paint = new Paint(getGraphics());
		state = new State(paint);
		keyPressed = false;
		k = 0;
		thread = null;
	}

	public boolean keyDown(Event e, int k) {
		keyPressed = true;
		action = e.key;
		return true;
	}

	public boolean lostFocus(Event e, Object w) {
		keyPressed = true;
		action = 'P';
		return true;
	}

	public void paint(Graphics graphics) {
		paint.showHelp();
		graphics.setColor(Color.black);

		paint.showLevel(state.level);
		paint.showScore(state.score);
		paint.drawField(state.newField);
		paint.drawFigure(state.x, state.y, state.figure);
		requestFocus();
	}
	

	public void run() {
		requestFocus();
		do {
			calculateTime = System.currentTimeMillis();
			state.launchNewFigure();
			paint.drawFigure(state.x, state.y, state.figure);
			while (checkBounds()) {
				if (checkTime()) {
					calculateTime = System.currentTimeMillis();
					paint.hideFigure(state.x, state.y);
					state.y++;
					paint.drawFigure(state.x, state.y, state.figure);
				}
				state.DScore = 0;
				do {
					Delay(50);
					if (keyPressed) {
						keyPressed = false;
						doApprropriateAction();
					}
				} while (!checkTime());
			}
			state.pasteFigure();
			do {
				checkChanges();
			} while (!state.noChanges);
		} while (!state.fullField());
	}

	private boolean checkTime() {
		return (int) (System.currentTimeMillis() - calculateTime) > (MAX_LEVEL - state.level) * TIME_SHIFT + MIN_TIME_SHIFT;
	}

	private boolean checkBounds() {
		return (state.y < HEIGHT - 2) && (state.newField.getValue(state.x, state.y + 3) == 0);
	}

	private void checkChanges() {
		state.noChanges = true;
		state.testField();
		if (!state.noChanges) {
			Delay(500);
			state.packField();
			paint.drawField(state.newField);
			state.score += state.DScore;
			paint.showScore(state.score);
			if (k >= FIG_TO_DROP) {
				k = 0;
				state.levelUp();
			}
		}
	}

	private void doApprropriateAction() {
		switch (action) {
		case Event.LEFT:
			if ((state.x > 1) && (state.newField.getValue(state.x - 1, state.y + 2) == 0)) {
				state.moveLeft();
			}
			break;
		case Event.RIGHT:
			if ((state.x < WIDTH) && (state.newField.getValue(state.x + 1, state.y + 2) == 0)) {
				state.moveRight();
			}
			break;
		case Event.UP:
			state.changeUp();
			break;
		case Event.DOWN:
			state.changeDown();
			break;
		case ' ':
			state.dropDown();
			calculateTime = 0;
			break;
		case 'P':
		case 'p':
			pauseGame();
			calculateTime = System.currentTimeMillis();
			break;
		case '-':
			k = 0;
			state.levelDown();
			break;
		case '+':
			k = 0;
			state.levelUp();
			break;
		}
	}

	private void pauseGame() {
		while (!keyPressed) {
			paint.hideFigure(state.x, state.y);
			Delay(500);
			paint.drawFigure(state.x, state.y, state.figure);
			Delay(500);
		}
	}

	public void start() {
		paint.graphics.setColor(Color.black);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}
}