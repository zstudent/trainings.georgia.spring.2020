package columns;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;

public class Columns extends Applet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int SL = 25;
	static final int HEIGHT = 15;
	static final int WIDTH = 7;
	static final int MAX_LEVEL = 7;
	static final int TIME_SHIFT = 250;
	static final int MIN_TIME_SHIFT = 200;
	static final int FIG_TO_DROP = 33;
	static final int LEFT_BORDER = 2;
	static final int TOP_BORDER = 2;

	static final Color gameColors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow,
			Color.pink, Color.magenta, Color.black };
	
	private int level;
	int i, j, ii, k, ch;
	long Score, DScore, tc;
	Font fCourier;
	Figure Fig;
	int Fnew[][], Fold[][];
	boolean NoChanges = true, KeyPressed = false;
	Graphics graphics;

	Thread thread = null;

	void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((Fnew[j][i] == Fnew[a][b]) && (Fnew[j][i] == Fnew[c][d])) {
			Fold[a][b] = 0;
			DrawBox(a, b, 8);
			Fold[j][i] = 0;
			DrawBox(j, i, 8);
			Fold[c][d] = 0;
			DrawBox(c, d, 8);
			NoChanges = false;
			Score += (level + 1) * 10;
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

	void DrawField(Graphics g) {
		int i, j;
		for (i = 1; i <= HEIGHT; i++) {
			for (j = 1; j <= WIDTH; j++) {
				DrawBox(j, i, Fnew[j][i]);
			}
		}
	}

	void DrawFigure(Figure f) {
		DrawBox(Figure.x, Figure.y, Figure.c[1]);
		DrawBox(Figure.x, Figure.y + 1, Figure.c[2]);
		DrawBox(Figure.x, Figure.y + 2, Figure.c[3]);
	}

	void DropFigure(Figure f) {
		int zz;
		if (Figure.y < HEIGHT - 2) {
			zz = HEIGHT;
			while (Fnew[Figure.x][zz] > 0)
				zz--;
			DScore = (((level + 1) * (HEIGHT * 2 - Figure.y - zz) * 2) % 5) * 5;
			Figure.y = zz - 2;
		}
	}

	boolean FullField() {
		int i;
		for (i = 1; i <= WIDTH; i++) {
			if (Fnew[i][3] > 0)
				return true;
		}
		return false;
	}

	void HideFigure(Figure f) {
		DrawBox(Figure.x, Figure.y, 0);
		DrawBox(Figure.x, Figure.y + 1, 0);
		DrawBox(Figure.x, Figure.y + 2, 0);
	}

	public void init() {
		Fnew = new int[WIDTH + 2][HEIGHT + 2];
		Fold = new int[WIDTH + 2][HEIGHT + 2];
		graphics = getGraphics();
	}

	public boolean keyDown(Event e, int k) {
		KeyPressed = true;
		ch = e.key;
		return true;
	}

	public boolean lostFocus(Event e, Object w) {
		KeyPressed = true;
		ch = 'P';
		return true;
	}

	void PackField() {
		int i, j, n;
		for (i = 1; i <= WIDTH; i++) {
			n = HEIGHT;
			for (j = HEIGHT; j > 0; j--) {
				if (Fold[i][j] > 0) {
					Fnew[i][n] = Fold[i][j];
					n--;
				}
			}
			;
			for (j = n; j > 0; j--)
				Fnew[i][j] = 0;
		}
	}

	public void paint(Graphics g) {
		ShowHelp(g);
		g.setColor(Color.black);

		ShowLevel();
		ShowScore();
		DrawField(g);
		DrawFigure(Fig);
		requestFocus();
	}

	void PasteFigure(Figure f) {
		Fnew[Figure.x][Figure.y] = Figure.c[1];
		Fnew[Figure.x][Figure.y + 1] = Figure.c[2];
		Fnew[Figure.x][Figure.y + 2] = Figure.c[3];
	}

	public void run() {
		for (i = 0; i < WIDTH + 1; i++) {
			for (j = 0; j < HEIGHT + 1; j++) {
				Fnew[i][j] = 0;
				Fold[i][j] = 0;
			}
		}
		level = 0;
		Score = 0;
		j = 0;
		k = 0;
		graphics.setColor(Color.black);
		requestFocus();

		do {
			tc = System.currentTimeMillis();
			new Figure();
			DrawFigure(Fig);
			while ((Figure.y < HEIGHT - 2) && (Fnew[Figure.x][Figure.y + 3] == 0)) {
				if ((int) (System.currentTimeMillis() - tc) > (MAX_LEVEL - level) * TIME_SHIFT + MIN_TIME_SHIFT) {
					tc = System.currentTimeMillis();
					HideFigure(Fig);
					Figure.y++;
					DrawFigure(Fig);
				}
				DScore = 0;
				do {
					Delay(50);
					if (KeyPressed) {
						KeyPressed = false;
						switch (ch) {
						case Event.LEFT:
							if ((Figure.x > 1) && (Fnew[Figure.x - 1][Figure.y + 2] == 0)) {
								HideFigure(Fig);
								Figure.x--;
								DrawFigure(Fig);
							}
							break;
						case Event.RIGHT:
							if ((Figure.x < WIDTH) && (Fnew[Figure.x + 1][Figure.y + 2] == 0)) {
								HideFigure(Fig);
								Figure.x++;
								DrawFigure(Fig);
							}
							break;
						case Event.UP:
							i = Figure.c[1];
							Figure.c[1] = Figure.c[2];
							Figure.c[2] = Figure.c[3];
							Figure.c[3] = i;
							DrawFigure(Fig);
							break;
						case Event.DOWN:
							i = Figure.c[1];
							Figure.c[1] = Figure.c[3];
							Figure.c[3] = Figure.c[2];
							Figure.c[2] = i;
							DrawFigure(Fig);
							break;
						case ' ':
							HideFigure(Fig);
							DropFigure(Fig);
							DrawFigure(Fig);
							tc = 0;
							break;
						case 'P':
						case 'p':
							while (!KeyPressed) {
								HideFigure(Fig);
								Delay(500);
								DrawFigure(Fig);
								Delay(500);
							}
							tc = System.currentTimeMillis();
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
				} while ((int) (System.currentTimeMillis() - tc) <= (MAX_LEVEL - level) * TIME_SHIFT + MIN_TIME_SHIFT);
			}
			;
			PasteFigure(Fig);
			do {
				NoChanges = true;
				TestField();
				if (!NoChanges) {
					Delay(500);
					PackField();
					DrawField(graphics);
					Score += DScore;
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

	void ShowHelp(Graphics g) {
		g.setColor(Color.black);

		g.drawString(" Keys available:", 200 - LEFT_BORDER, 102);
		g.drawString("Roll Box Up:     ", 200 - LEFT_BORDER, 118);
		g.drawString("Roll Box Down:   ", 200 - LEFT_BORDER, 128);
		g.drawString("Figure Left:     ", 200 - LEFT_BORDER, 138);
		g.drawString("Figure Right:    ", 200 - LEFT_BORDER, 148);
		g.drawString("Level High/Low: +/-", 200 - LEFT_BORDER, 158);
		g.drawString("Drop Figure:   space", 200 - LEFT_BORDER, 168);
		g.drawString("Pause:           P", 200 - LEFT_BORDER, 180);
		g.drawString("Quit:     Esc or Q", 200 - LEFT_BORDER, 190);
	}

	void ShowLevel() {
		graphics.setColor(Color.black);
		graphics.clearRect(LEFT_BORDER + 100, 390, 100, 20);
		graphics.drawString("Level: " + level, LEFT_BORDER + 100, 400);
	}

	void ShowScore() {
		graphics.setColor(Color.black);
		graphics.clearRect(LEFT_BORDER, 390, 100, 20);
		graphics.drawString("Score: " + Score, LEFT_BORDER, 400);
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
		int i, j;
		for (i = 1; i <= HEIGHT; i++) {
			for (j = 1; j <= WIDTH; j++) {
				Fold[j][i] = Fnew[j][i];
			}
		}
		for (i = 1; i <= HEIGHT; i++) {
			for (j = 1; j <= WIDTH; j++) {
				if (Fnew[j][i] > 0) {
					CheckNeighbours(j, i - 1, j, i + 1, i, j);
					CheckNeighbours(j - 1, i, j + 1, i, i, j);
					CheckNeighbours(j - 1, i - 1, j + 1, i + 1, i, j);
					CheckNeighbours(j + 1, i - 1, j - 1, i + 1, i, j);
				}
			}
		}
	}
}