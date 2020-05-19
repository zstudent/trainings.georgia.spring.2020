package columns;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class Columns extends Applet implements Runnable {
	static final int SL = 25, Depth = 15, Width = 7, MaxLevel = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200,
			LeftBorder = 2, TopBorder = 2;

	Color MyStyles[] = { Color.black, Color.cyan, Color.blue, Color.red,
			Color.green, Color.yellow, Color.pink, Color.magenta,
			Color.black };

	int Level, i, j, ii, k;
	int ch;
	long score, dropScore, timestamp;
	Font fCourier;
	Figure Fig;
	int newField[][], oldField[][];
	boolean NoChanges = true, keyPressed = false;
	Graphics _gr;

	Thread thr = null;

	void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((newField[j][i] == newField[a][b]) && (newField[j][i] == newField[c][d])) {
			oldField[a][b] = 0;
			DrawBox(a, b, 8);
			oldField[j][i] = 0;
			DrawBox(j, i, 8);
			oldField[c][d] = 0;
			DrawBox(c, d, 8);
			NoChanges = false;
			score += (Level + 1) * 10;
			k++;
		}
		;
	}

	void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
		;
	}

	void DrawBox(int x, int y, int c) {
		if (c == 0) {
			_gr.setColor(Color.black);
			_gr.fillRect(LeftBorder + x * SL - SL,
					TopBorder + y * SL - SL, SL, SL);
			_gr.drawRect(LeftBorder + x * SL - SL,
					TopBorder + y * SL - SL, SL, SL);
		} else if (c == 8) {
			_gr.setColor(Color.white);
			_gr.drawRect(LeftBorder + x * SL - SL + 1,
					TopBorder + y * SL - SL + 1, SL - 2, SL - 2);
			_gr.drawRect(LeftBorder + x * SL - SL + 2,
					TopBorder + y * SL - SL + 2, SL - 4, SL - 4);
			_gr.setColor(Color.black);
			_gr.fillRect(LeftBorder + x * SL - SL + 3,
					TopBorder + y * SL - SL + 3, SL - 6, SL - 6);
		} else {
			_gr.setColor(MyStyles[c]);
			_gr.fillRect(LeftBorder + x * SL - SL,
					TopBorder + y * SL - SL, SL, SL);
			_gr.setColor(Color.black);
			_gr.drawRect(LeftBorder + x * SL - SL,
					TopBorder + y * SL - SL, SL, SL);
		}
		// g.setColor (Color.black);
	}

	void DrawField(Graphics g) {
		int i, j;
		for (i = 1; i <= Depth; i++) {
			for (j = 1; j <= Width; j++) {
				DrawBox(j, i, newField[j][i]);
			}
		}
	}

	void DrawFigure(Figure f) {
		DrawBox(f.x, f.y, f.c[1]);
		DrawBox(f.x, f.y + 1, f.c[2]);
		DrawBox(f.x, f.y + 2, f.c[3]);
	}

	void DropFigure(Figure f) {
		int zz;
		if (f.y < Depth - 2) {
			zz = Depth;
			while (newField[f.x][zz] > 0)
				zz--;
			dropScore = (((Level + 1) * (Depth * 2 - f.y - zz) * 2) % 5)
					* 5;
			f.y = zz - 2;
		}
	}

	boolean isGameOver() {
		for (int i = 1; i <= Width; i++) {
			if (newField[i][3] > 0)
				return true;
		}
		return false;
	}

	void HideFigure(Figure f) {
		DrawBox(f.x, f.y, 0);
		DrawBox(f.x, f.y + 1, 0);
		DrawBox(f.x, f.y + 2, 0);
	}

	@Override
	public void init() {
		newField = new int[Width + 2][Depth + 2];
		oldField = new int[Width + 2][Depth + 2];
		_gr = getGraphics();
	}

	@Override
	public boolean keyDown(Event e, int k) {
		keyPressed = true;
		ch = e.key;
		return true;
	}

	@Override
	public boolean lostFocus(Event e, Object w) {
		keyPressed = true;
		ch = 'P';
		return true;
	}

	void PackField() {
		int i, j, n;
		for (i = 1; i <= Width; i++) {
			n = Depth;
			for (j = Depth; j > 0; j--) {
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

	@Override
	public void paint(Graphics g) {
		// ShowHelp(g);

		g.setColor(Color.black);

		ShowLevel(g);
		ShowScore(g);
		DrawField(g);
		DrawFigure(Fig);
		requestFocus();
	}

	void pasteFigure(Figure f) {
		newField[f.x][f.y] = f.c[1];
		newField[f.x][f.y + 1] = f.c[2];
		newField[f.x][f.y + 2] = f.c[3];
	}

	public void run() {
		initState();
		initView();

		do {
			timestamp = System.currentTimeMillis();
			new Figure();
			DrawFigure(Fig);
			slideFigureDownUntilBottomReached();
			pasteFigure(Fig);
			do {
				removeTriples();
			} while (!NoChanges);
		} while (!isGameOver());
	}

	private void removeTriples() {
		NoChanges = true;
		TestField();
		if (!NoChanges) {
			Delay(500);
			PackField();
			DrawField(_gr);
			score += dropScore;
			ShowScore(_gr);
			if (k >= FigToDrop) {
				k = 0;
				if (Level < MaxLevel)
					Level++;
				ShowLevel(_gr);
			}
		}
	}

	private void slideFigureDownUntilBottomReached() {
		while (canFigureSlideDown()) {
			if (millisSinceLastSlideDown() > timeFrameForSlidingDown()) {
				slideFigureDown();
			}
			dropScore = 0;
			listenForKeyPressedUntilNextSlideDown();
		}
	}

	private void listenForKeyPressedUntilNextSlideDown() {
		do {
			Delay(50);
			if (keyPressed)
				reactToKeyAction();
		} while (millisSinceLastSlideDown() <= timeFrameForSlidingDown());
	}

	private void reactToKeyAction() {
		{
			keyPressed = false;
			switch (ch) {
			case Event.LEFT:
				if (canWeMoveToTheLeft()) {
					HideFigure(Fig);
					Fig.x--;
					DrawFigure(Fig);
				}
				break;
			case Event.RIGHT:
				if (canWeMoveToTheRight()) {
					HideFigure(Fig);
					Fig.x++;
					DrawFigure(Fig);
				}
				break;
			case Event.UP:
				rotateFigureColorsUp();
				DrawFigure(Fig);
				break;
			case Event.DOWN:
				rotateFigureColorsDown();
				DrawFigure(Fig);
				break;
			case ' ':
				HideFigure(Fig);
				DropFigure(Fig);
				DrawFigure(Fig);
				timestamp = 0;
				break;
			case 'P':
			case 'p':
				while (!keyPressed) {
					HideFigure(Fig);
					Delay(500);
					DrawFigure(Fig);
					Delay(500);
				}
				timestamp = System.currentTimeMillis();
				break;
			case '-':
				if (Level > 0)
					Level--;
				k = 0;
				ShowLevel(_gr);
				break;
			case '+':
				if (Level < MaxLevel)
					Level++;
				k = 0;
				ShowLevel(_gr);
				break;
			}
		}
	}

	private void rotateFigureColorsDown() {
		i = Fig.c[1];
		Fig.c[1] = Fig.c[3];
		Fig.c[3] = Fig.c[2];
		Fig.c[2] = i;
	}

	private void rotateFigureColorsUp() {
		i = Fig.c[1];
		Fig.c[1] = Fig.c[2];
		Fig.c[2] = Fig.c[3];
		Fig.c[3] = i;
	}

	private boolean canWeMoveToTheRight() {
		return (Fig.x < Width)
				&& (newField[Fig.x + 1][Fig.y
						+ 2] == 0);
	}

	private boolean canWeMoveToTheLeft() {
		return (Fig.x > 1) && (newField[Fig.x - 1][Fig.y
				+ 2] == 0);
	}

	private void slideFigureDown() {
		timestamp = System.currentTimeMillis();
		HideFigure(Fig);
		Fig.y++;
		DrawFigure(Fig);
	}

	private int timeFrameForSlidingDown() {
		return (MaxLevel - Level) * TimeShift
						+ MinTimeShift;
	}

	private int millisSinceLastSlideDown() {
		return (int) (System.currentTimeMillis()
				- timestamp);
	}

	private boolean canFigureSlideDown() {
		return (Fig.y < Depth - 2)
				&& (newField[Fig.x][Fig.y + 3] == 0);
	}

	private void initView() {
		_gr.setColor(Color.black);
		requestFocus();
	}

	private void initState() {
		for (i = 0; i < Width + 1; i++) {
			for (j = 0; j < Depth + 1; j++) {
				newField[i][j] = 0;
				oldField[i][j] = 0;
			}
		}
		Level = 0;
		score = 0;
		j = 0;
		k = 0;
	}

	void ShowHelp(Graphics g) {
		g.setColor(Color.black);

		g.drawString(" Keys available:", 200 - LeftBorder, 102);
		g.drawString("Roll Box Up:     ", 200 - LeftBorder, 118);
		g.drawString("Roll Box Down:   ", 200 - LeftBorder, 128);
		g.drawString("Figure Left:     ", 200 - LeftBorder, 138);
		g.drawString("Figure Right:    ", 200 - LeftBorder, 148);
		g.drawString("Level High/Low: +/-", 200 - LeftBorder, 158);
		g.drawString("Drop Figure:   space", 200 - LeftBorder, 168);
		g.drawString("Pause:           P", 200 - LeftBorder, 180);
		g.drawString("Quit:     Esc or Q", 200 - LeftBorder, 190);
	}

	void ShowLevel(Graphics g) {
		g.setColor(Color.black);
		g.clearRect(LeftBorder + 100, 390, 100, 20);
		g.drawString("Level: " + Level, LeftBorder + 100, 400);
	}

	void ShowScore(Graphics g) {
		g.setColor(Color.black);
		g.clearRect(LeftBorder, 390, 100, 20);
		g.drawString("Score: " + score, LeftBorder, 400);
	}

	@Override
	public void start() {
		_gr.setColor(Color.black);

		// setBackground (new Color(180,180,180));

		if (thr == null) {
			thr = new Thread(this);
			thr.start();
		}
	}

	@Override
	public void stop() {
		if (thr != null) {
			thr.stop();
			thr = null;
		}
	}

	void TestField() {
		int i, j;
		for (i = 1; i <= Depth; i++) {
			for (j = 1; j <= Width; j++) {
				oldField[j][i] = newField[j][i];
			}
		}
		for (i = 1; i <= Depth; i++) {
			for (j = 1; j <= Width; j++) {
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