package columns;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import columns.ModelEvent.Delay;
import columns.ModelEvent.DrawField;
import columns.ModelEvent.DrawFigure;
import columns.ModelEvent.HideFigure;
import columns.ModelEvent.ShowLevel;
import columns.ModelEvent.ShowScore;

public class Model {

	int newField[][];
	int oldField[][];
	private int figuresCollectedOnThisLevel;
	private int Level;
	private long score;
	private int dropScore;
	private boolean NoChanges = true;

	private Columns columns;
	int y = 1;
	int x = Columns.Width / 2 + 1;
	int c[] = new int[4];
	static Random r = new Random();

	public Model(Columns columns) {
		this.columns = columns;
		initState();
	}

	int getFiguresCollectedOnThisLevel() {
		return figuresCollectedOnThisLevel;
	}

	void setFiguresCollectedOnThisLevel(
			int figuresCollectedOnThisLevel) {
		this.figuresCollectedOnThisLevel = figuresCollectedOnThisLevel;
	}

	int getLevel() {
		return Level;
	}

	void setLevel(int level) {
		Level = level;
	}

	long getScore() {
		return score;
	}

	void setScore(long score) {
		this.score = score;
	}

	int getDropScore() {
		return dropScore;
	}

	void setDropScore(int dropScore) {
		this.dropScore = dropScore;
	}

	boolean isNoChanges() {
		return NoChanges;
	}

	void setNoChanges(boolean noChanges) {
		NoChanges = noChanges;
	}

	void checkNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((newField[j][i] == newField[a][b])
				&& (newField[j][i] == newField[c][d])) {
			oldField[a][b] = 0;
			columns.view.DrawBox(a, b, 8);
			oldField[j][i] = 0;
			columns.view.DrawBox(j, i, 8);
			oldField[c][d] = 0;
			columns.view.DrawBox(c, d, 8);
			setNoChanges(false);
			setScore(getScore() + (getLevel() + 1) * 10);
			setFiguresCollectedOnThisLevel(
					getFiguresCollectedOnThisLevel() + 1);
		}
	}

	boolean canSlideFigureDown() {
		return (y < Columns.Depth - 2)
				&& (newField[x][y + 3] == 0);
	}

	boolean canWeMoveToTheLeft() {
		return (x > 1)
				&& (newField[x - 1][y + 2] == 0);
	}

	boolean canWeMoveToTheRight() {
		return (x < Columns.Width)
				&& (newField[x + 1][y + 2] == 0);
	}

	void DropFigure() {
		int zz;
		if (y < Columns.Depth - 2) {
			zz = Columns.Depth;
			while (newField[x][zz] > 0)
				zz--;
			setDropScore((((getLevel() + 1)
					* (Columns.Depth * 2 - y - zz) * 2) % 5) * 5);
			y = zz - 2;
		}
	}

	boolean isGameOver() {
		for (int i = 1; i <= Columns.Width; i++) {
			if (newField[i][3] > 0)
				return true;
		}
		return false;
	}

	void PackField() {
		int i, j, n;
		for (i = 1; i <= Columns.Width; i++) {
			n = Columns.Depth;
			for (j = Columns.Depth; j > 0; j--) {
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

	void rotateFigureColorsDown() {
		int i = c[1];
		c[1] = c[3];
		c[3] = c[2];
		c[2] = i;
	}

	void rotateFigureColorsUp() {
		int i = c[1];
		c[1] = c[2];
		c[2] = c[3];
		c[3] = i;
	}

	void pasteFigure() {
		newField[x][y] = c[1];
		newField[x][y + 1] = c[2];
		newField[x][y + 2] = c[3];
	}

	void TestField() {
		int i, j;
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				oldField[j][i] = newField[j][i];
			}
		}
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				if (newField[j][i] > 0) {
					checkNeighbours(j, i - 1, j, i + 1, i, j);
					checkNeighbours(j - 1, i, j + 1, i, i, j);
					checkNeighbours(j - 1, i - 1, j + 1, i + 1, i, j);
					checkNeighbours(j + 1, i - 1, j - 1, i + 1, i, j);
				}
			}
		}
	}

	private void initState() {
		this.newField = new int[Columns.Width + 2][Columns.Depth + 2];
		this.oldField = new int[Columns.Width + 2][Columns.Depth + 2];
		for (int i = 0; i < Columns.Width + 1; i++) {
			for (int j = 0; j < Columns.Depth + 1; j++) {
				newField[i][j] = 0;
				oldField[i][j] = 0;
			}
		}
		setLevel(0);
		setScore(0);
		setFiguresCollectedOnThisLevel(0);
	}

	void createNewFigure() {
		x = Columns.Width / 2 + 1;
		y = 1;
		c[0] = 0;
		c[1] = (int) (Math.abs(r.nextInt()) % 7 + 1);
		c[2] = (int) (Math.abs(r.nextInt()) % 7 + 1);
		c[3] = (int) (Math.abs(r.nextInt()) % 7 + 1);
	}

	List<ModelEvent> reactToKeyAction(int ch) {
		List<ModelEvent> events = new ArrayList<ModelEvent>();
		switch (ch) {
		case KeyEvent.VK_LEFT:
			if (canWeMoveToTheLeft()) {
				events.add(new HideFigure(this));
				x = x - 1;
				events.add(new DrawFigure(this));
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (canWeMoveToTheRight()) {
				events.add(new HideFigure(this));
				x = x + 1;
				events.add(new DrawFigure(this));
			}
			break;
		case KeyEvent.VK_UP:
			rotateFigureColorsUp();
			events.add(new DrawFigure(this));
			
			break;
		case KeyEvent.VK_DOWN:
			rotateFigureColorsDown();
			events.add(new DrawFigure(this));
			break;
		case KeyEvent.VK_SPACE:
			events.add(new HideFigure(this));
			DropFigure();
			events.add(new DrawFigure(this));
			break;
		case KeyEvent.VK_P:
			while (!columns.keyPressed) {
				events.add(new HideFigure(this));
				events.add(new Delay(500));
				events.add(new DrawFigure(this));
				events.add(new Delay(500));
			}
			break;
		case KeyEvent.VK_MINUS:
			if (getLevel() > 0)
				setLevel(getLevel() - 1);
			setFiguresCollectedOnThisLevel(0);
			events.add(new ShowLevel(getLevel()));
			break;
		case KeyEvent.VK_PLUS:
			if (getLevel() < Columns.MaxLevel)
				setLevel(getLevel() + 1);
			setFiguresCollectedOnThisLevel(0);
			events.add(new ShowLevel(getLevel()));
			break;
		}
		return events;
	}

	List<ModelEvent> slideFigureDown(Columns columns) {
		List<ModelEvent> events = new ArrayList<ModelEvent>();
		if (canSlideFigureDown()) {
			events.add(new HideFigure(this));
			y++;
			setDropScore(0);
			events.add(new DrawFigure(this));
		} else {
			pasteFigure();
			do {
				events.addAll(columns.model.removeTriples(columns));
			} while (!isNoChanges());
			if (!isGameOver()) {
				events.addAll(columns.model.launchNewFigure());
			} else {
				// TODO game over code
			}
		}
		return events;
	}

	List<ModelEvent> removeTriples(Columns columns) {
		List<ModelEvent> events = new ArrayList<ModelEvent>();
		setNoChanges(true);
		TestField();
		if (!isNoChanges()) {
			events.add(new Delay(500));
			PackField();
			events.add(new DrawField(newField));
			setScore(getScore() + getDropScore());
			events.add(new ShowScore(getScore()));
			if (getFiguresCollectedOnThisLevel() >= Columns.FigToDrop) {
				setFiguresCollectedOnThisLevel(0);
				if (getLevel() < Columns.MaxLevel)
					setLevel(getLevel() + 1);
				events.add(new ShowLevel(getLevel()));
			}
		}
		return events;
	}

	List<ModelEvent> launchNewFigure() {
		createNewFigure();
		return Arrays.asList(new DrawFigure(this));
	}

}
