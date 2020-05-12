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
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}

	public void init() {
		paint = new Paint(getGraphics());
		state = new State(paint);
		keyPressed = false;
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
		state.drawField();
		state.drawFigure();
		requestFocus();
	}
	

	public void run() {
		k = 0;
		paint.graphics.setColor(Color.black);
		requestFocus();

		do {
			calculateTime = System.currentTimeMillis();
			state.launchNewFigure();
			state.drawFigure();
			while ((state.y < HEIGHT - 2) && (state.newField.getValue(state.x, state.y + 3) == 0)) {
				if ((int) (System.currentTimeMillis() - calculateTime) > (MAX_LEVEL - state.level) * TIME_SHIFT + MIN_TIME_SHIFT) {
					calculateTime = System.currentTimeMillis();
					state.hideFigure();
					state.y++;
					state.drawFigure();
				}
				state.DScore = 0;
				do {
					Delay(50);
					if (keyPressed) {
						keyPressed = false;
						switch (action) {
						case Event.LEFT:
							if ((state.x > 1) && (state.newField.getValue(state.x - 1, state.y + 2) == 0)) {
								state.hideFigure();
								state.x--;
								state.drawFigure();
							}
							break;
						case Event.RIGHT:
							if ((state.x < WIDTH) && (state.newField.getValue(state.x + 1, state.y + 2) == 0)) {
								state.hideFigure();
								state.x++;
								state.drawFigure();
							}
							break;
						case Event.UP:
							int tmp = state.figure.data[1];
							state.figure.data[1] = state.figure.data[2];
							state.figure.data[2] = state.figure.data[3];
							state.figure.data[3] = tmp;
							state.drawFigure();
							break;
						case Event.DOWN:
							int tmp1  = state.figure.data[1];
							state.figure.data[1] = state.figure.data[3];
							state.figure.data[3] = state.figure.data[2];
							state.figure.data[2] = tmp1;
							state.drawFigure();
							break;
						case ' ':
							state.hideFigure();
							state.dropFigure();
							state.drawFigure();
							calculateTime = 0;
							break;
						case 'P':
						case 'p':
							while (!keyPressed) {
								state.hideFigure();
								Delay(500);
								state.drawFigure();
								Delay(500);
							}
							calculateTime = System.currentTimeMillis();
							break;
						case '-':
							if (state.level > 0)
								state.level--;
							k = 0;
							paint.showLevel(state.level);
							break;
						case '+':
							if (state.level < MAX_LEVEL)
								state.level++;
							k = 0;
							paint.showLevel(state.level);
							break;
						}
					}
				} while ((int) (System.currentTimeMillis() - calculateTime) <= (MAX_LEVEL - state.level) * TIME_SHIFT + MIN_TIME_SHIFT);
			}
			state.pasteFigure();
			do {
				state.noChanges = true;
				state.testField();
				if (!state.noChanges) {
					Delay(500);
					state.packField();
					state.drawField();
					state.score += state.DScore;
					paint.showScore(state.score);
					if (k >= FIG_TO_DROP) {
						k = 0;
						if (state.level < MAX_LEVEL)
							state.level++;
						paint.showLevel(state.level);
					}
				}
			} while (!state.noChanges);
		} while (!state.fullField());
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