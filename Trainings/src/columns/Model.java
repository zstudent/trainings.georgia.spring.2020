package columns;

public class Model {

	private int newField[][];
	private int oldField[][];
	private int figuresCollectedOnThisLevel;
	private int Level;
	private Figure Fig;
	private long score;
	private int dropScore;
	private boolean NoChanges = true;
	
	private Columns columns;
	
	public Model(Columns columns) {
		this.columns = columns;
	}
	
	int[][] getNewField() {
		return newField;
	}
	void setNewField(int[][] newField) {
		this.newField = newField;
	}
	int[][] getOldField() {
		return oldField;
	}
	void setOldField(int[][] oldField) {
		this.oldField = oldField;
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
	Figure getFig() {
		return Fig;
	}
	void setFig(Figure fig) {
		Fig = fig;
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
		if ((getNewField()[j][i] == getNewField()[a][b]) && (getNewField()[j][i] == getNewField()[c][d])) {
			getOldField()[a][b] = 0;
			columns.view.DrawBox(a, b, 8);
			getOldField()[j][i] = 0;
			columns.view.DrawBox(j, i, 8);
			getOldField()[c][d] = 0;
			columns.view.DrawBox(c, d, 8);
			setNoChanges(false);
			setScore(getScore() + (getLevel() + 1) * 10);
			setFiguresCollectedOnThisLevel(
					getFiguresCollectedOnThisLevel() + 1);
		}
	}

	boolean canFigureSlideDown() {
		return (getFig().y < Columns.Depth - 2)
				&& (getNewField()[getFig().x][getFig().y + 3] == 0);
	}

	boolean canWeMoveToTheLeft() {
		return (getFig().x > 1) && (getNewField()[getFig().x - 1][getFig().y
				+ 2] == 0);
	}

	boolean canWeMoveToTheRight() {
		return (getFig().x < Columns.Width)
				&& (getNewField()[getFig().x + 1][getFig().y
						+ 2] == 0);
	}

	void DropFigure(Figure f) {
		int zz;
		if (f.y < Columns.Depth - 2) {
			zz = Columns.Depth;
			while (getNewField()[f.x][zz] > 0)
				zz--;
			setDropScore((((getLevel() + 1) * (Columns.Depth * 2 - f.y - zz) * 2) % 5)
					* 5);
			f.y = zz - 2;
		}
	}

	boolean isGameOver() {
		for (int i = 1; i <= Columns.Width; i++) {
			if (getNewField()[i][3] > 0)
				return true;
		}
		return false;
	}

	void PackField() {
		int i, j, n;
		for (i = 1; i <= Columns.Width; i++) {
			n = Columns.Depth;
			for (j = Columns.Depth; j > 0; j--) {
				if (getOldField()[i][j] > 0) {
					getNewField()[i][n] = getOldField()[i][j];
					n--;
				}
			}
			;
			for (j = n; j > 0; j--)
				getNewField()[i][j] = 0;
		}
	}

	void rotateFigureColorsDown() {
		int i = getFig().c[1];
		getFig().c[1] = getFig().c[3];
		getFig().c[3] = getFig().c[2];
		getFig().c[2] = i;
	}

	void rotateFigureColorsUp() {
		int i = getFig().c[1];
		getFig().c[1] = getFig().c[2];
		getFig().c[2] = getFig().c[3];
		getFig().c[3] = i;
	}

	void pasteFigure(Figure f) {
		getNewField()[f.x][f.y] = f.c[1];
		getNewField()[f.x][f.y + 1] = f.c[2];
		getNewField()[f.x][f.y + 2] = f.c[3];
	}

	void TestField() {
		int i, j;
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				getOldField()[j][i] = getNewField()[j][i];
			}
		}
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				if (getNewField()[j][i] > 0) {
					checkNeighbours(j, i - 1, j, i + 1, i, j);
					checkNeighbours(j - 1, i, j + 1, i, i, j);
					checkNeighbours(j - 1, i - 1, j + 1, i + 1, i, j);
					checkNeighbours(j + 1, i - 1, j - 1, i + 1, i, j);
				}
			}
		}
	}

	void initState() {
		setNewField(new int[Columns.Width + 2][Columns.Depth + 2]);
		setOldField(new int[Columns.Width + 2][Columns.Depth + 2]);
		for (int i = 0; i < Columns.Width + 1; i++) {
			for (int j = 0; j < Columns.Depth + 1; j++) {
				getNewField()[i][j] = 0;
				getOldField()[i][j] = 0;
			}
		}
		setLevel(0);
		setScore(0);
		setFiguresCollectedOnThisLevel(0);
	}

}
