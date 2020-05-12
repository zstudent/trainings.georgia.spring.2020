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
	
	
	private int level, k, action;
	private long score, DScore, calculateTime;
	private int newField[][], oldField[][];
	private boolean NoChanges, KeyPressed;
	private Graphics graphics;

	private Thread thread;

	void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((newField[j][i] == newField[a][b]) && (newField[j][i] == newField[c][d])) {
			oldField[a][b] = 0;
			DrawBox(a, b, 8);
			oldField[j][i] = 0;
			DrawBox(j, i, 8);
			oldField[c][d] = 0;
			DrawBox(c, d, 8);
			NoChanges = false;
			score += (level + 1) * 10;
			k++;
		}
	}

	void Delay(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void DrawBox(int x, int y, int c) {
		if (c == 0) {
			graphics.setColor(Color.black);
			graphics.fillRect(LEFT_BORDER + x * SL - SL, TOP_BORDER + y * SL - SL, SL, SL);
			graphics.drawRect(LEFT_BORDER + x * SL - SL, TOP_BORDER + y * SL - SL, SL, SL);
		} else if (c == 8) {
			graphics.setColor(Color.white);
			graphics.drawRect(LEFT_BORDER + x * SL - SL + 1, TOP_BORDER + y * SL - SL + 1, SL - 2, SL - 2);
			graphics.drawRect(LEFT_BORDER + x * SL - SL + 2, TOP_BORDER + y * SL - SL + 2, SL - 4, SL - 4);
			graphics.setColor(Color.black);
			graphics.fillRect(LEFT_BORDER + x * SL - SL + 3, TOP_BORDER + y * SL - SL + 3, SL - 6, SL - 6);
		} else {
			graphics.setColor(gameColors[c]);
			graphics.fillRect(LEFT_BORDER + x * SL - SL, TOP_BORDER + y * SL - SL, SL, SL);
			graphics.setColor(Color.black);
			graphics.drawRect(LEFT_BORDER + x * SL - SL, TOP_BORDER + y * SL - SL, SL, SL);
		}
	}

	void DrawField() {
		int i, j;
		for (i = 1; i <= HEIGHT; i++) {
			for (j = 1; j <= WIDTH; j++) {
				DrawBox(j, i, newField[j][i]);
			}
		}
	}

	void DrawFigure() {
		DrawBox(Figure.x, Figure.y, Figure.c[1]);
		DrawBox(Figure.x, Figure.y + 1, Figure.c[2]);
		DrawBox(Figure.x, Figure.y + 2, Figure.c[3]);
	}

	void DropFigure() {
		int zz;
		if (Figure.y < HEIGHT - 2) {
			zz = HEIGHT;
			while (newField[Figure.x][zz] > 0)
				zz--;
			DScore = (((level + 1) * (HEIGHT * 2 - Figure.y - zz) * 2) % 5) * 5;
			Figure.y = zz - 2;
		}
	}

	boolean FullField() {
		for (int i = 1; i <= WIDTH; i++) {
			if (newField[i][3] > 0)
				return true;
		}
		return false;
	}

	void HideFigure() {
		DrawBox(Figure.x, Figure.y, 0);
		DrawBox(Figure.x, Figure.y + 1, 0);
		DrawBox(Figure.x, Figure.y + 2, 0);
	}

	public void init() {
		newField = new int[WIDTH + 2][HEIGHT + 2];
		oldField = new int[WIDTH + 2][HEIGHT + 2];
		NoChanges = true;
		KeyPressed = false;
		level = 0;
		score = 0;
		thread = null;
		graphics = getGraphics();
	}

	public boolean keyDown(Event e, int k) {
		KeyPressed = true;
		action = e.key;
		return true;
	}

	public boolean lostFocus(Event e, Object w) {
		KeyPressed = true;
		action = 'P';
		return true;
	}

	void PackField() {
		int i, j, n;
		for (i = 1; i <= WIDTH; i++) {
			n = HEIGHT;
			for (j = HEIGHT; j > 0; j--) {
				if (oldField[i][j] > 0) {
					newField[i][n] = oldField[i][j];
					n--;
				}
			}
			;
			for (j = n; j > 0; j--)
				newField[i][j] = 0;
		}
	}

	public void paint(Graphics graphics) {
		ShowHelp();
		graphics.setColor(Color.black);

		ShowLevel();
		ShowScore();
		DrawField();
		DrawFigure();
		requestFocus();
	}

	void PasteFigure() {
		newField[Figure.x][Figure.y] = Figure.c[1];
		newField[Figure.x][Figure.y + 1] = Figure.c[2];
		newField[Figure.x][Figure.y + 2] = Figure.c[3];
	}

	public void run() {
		k = 0;
		graphics.setColor(Color.black);
		requestFocus();

		do {
			calculateTime = System.currentTimeMillis();
			new Figure();
			DrawFigure();
			while ((Figure.y < HEIGHT - 2) && (newField[Figure.x][Figure.y + 3] == 0)) {
				if ((int) (System.currentTimeMillis() - calculateTime) > (MAX_LEVEL - level) * TIME_SHIFT + MIN_TIME_SHIFT) {
					calculateTime = System.currentTimeMillis();
					HideFigure();
					Figure.y++;
					DrawFigure();
				}
				DScore = 0;
				do {
					Delay(50);
					if (KeyPressed) {
						KeyPressed = false;
						switch (action) {
						case Event.LEFT:
							if ((Figure.x > 1) && (newField[Figure.x - 1][Figure.y + 2] == 0)) {
								HideFigure();
								Figure.x--;
								DrawFigure();
							}
							break;
						case Event.RIGHT:
							if ((Figure.x < WIDTH) && (newField[Figure.x + 1][Figure.y + 2] == 0)) {
								HideFigure();
								Figure.x++;
								DrawFigure();
							}
							break;
						case Event.UP:
							int tmp = Figure.c[1];
							Figure.c[1] = Figure.c[2];
							Figure.c[2] = Figure.c[3];
							Figure.c[3] = tmp;
							DrawFigure();
							break;
						case Event.DOWN:
							int tmp1  = Figure.c[1];
							Figure.c[1] = Figure.c[3];
							Figure.c[3] = Figure.c[2];
							Figure.c[2] = tmp1;
							DrawFigure();
							break;
						case ' ':
							HideFigure();
							DropFigure();
							DrawFigure();
							calculateTime = 0;
							break;
						case 'P':
						case 'p':
							while (!KeyPressed) {
								HideFigure();
								Delay(500);
								DrawFigure();
								Delay(500);
							}
							calculateTime = System.currentTimeMillis();
							break;
						case '-':
							if (level > 0)
								level--;
							k = 0;
							ShowLevel();
							break;
						case '+':
							if (level < MAX_LEVEL)
								level++;
							k = 0;
							ShowLevel();
							break;
						}
					}
				} while ((int) (System.currentTimeMillis() - calculateTime) <= (MAX_LEVEL - level) * TIME_SHIFT + MIN_TIME_SHIFT);
			}
			;
			PasteFigure();
			do {
				NoChanges = true;
				TestField();
				if (!NoChanges) {
					Delay(500);
					PackField();
					DrawField();
					score += DScore;
					ShowScore();
					if (k >= FIG_TO_DROP) {
						k = 0;
						if (level < MAX_LEVEL)
							level++;
						ShowLevel();
					}
				}
			} while (!NoChanges);
		} while (!FullField());
	}

	void ShowHelp() {
		graphics.setColor(Color.black);
		graphics.drawString(" Keys available:", 200 - LEFT_BORDER, 102);
		graphics.drawString("Roll Box Up:     ", 200 - LEFT_BORDER, 118);
		graphics.drawString("Roll Box Down:   ", 200 - LEFT_BORDER, 128);
		graphics.drawString("Figure Left:     ", 200 - LEFT_BORDER, 138);
		graphics.drawString("Figure Right:    ", 200 - LEFT_BORDER, 148);
		graphics.drawString("Level High/Low: +/-", 200 - LEFT_BORDER, 158);
		graphics.drawString("Drop Figure:   space", 200 - LEFT_BORDER, 168);
		graphics.drawString("Pause:           P", 200 - LEFT_BORDER, 180);
		graphics.drawString("Quit:     Esc or Q", 200 - LEFT_BORDER, 190);
	}

	void ShowLevel() {
		graphics.setColor(Color.black);
		graphics.clearRect(LEFT_BORDER + 100, 390, 100, 20);
		graphics.drawString("Level: " + level, LEFT_BORDER + 100, 400);
	}

	void ShowScore() {
		graphics.setColor(Color.black);
		graphics.clearRect(LEFT_BORDER, 390, 100, 20);
		graphics.drawString("Score: " + score, LEFT_BORDER, 400);
	}

	public void start() {
		graphics.setColor(Color.black);
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

	void TestField() {
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				oldField[j][i] = newField[j][i];
			}
		}
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				if (newField[j][i] > 0) {
					CheckNeighbours(j, i - 1, j, i + 1, i, j);
					CheckNeighbours(j - 1, i, j + 1, i, i, j);
					CheckNeighbours(j - 1, i - 1, j + 1, i + 1, i, j);
					CheckNeighbours(j + 1, i - 1, j - 1, i + 1, i, j);
				}
			}
		}
	}
}