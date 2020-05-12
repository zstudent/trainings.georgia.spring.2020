package columns;

public class State {
	protected Field newField;
	protected Field oldField;
	protected Figure figure;
	protected Paint paint;
	protected int HEIGHT;
	protected int WIDTH;
	protected int x;
	protected int y;
	protected int level, score, k, DScore;
	protected boolean noChanges;
	
	public State(Paint paint) {
		newField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		oldField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		this.paint = paint;
		HEIGHT = Columns.HEIGHT;
		WIDTH = Columns.WIDTH;
		level = 0;
		score = 0;
		noChanges = true;
		
		launchNewFigure();
	}

	public void launchNewFigure() {
		figure = new Figure();
		x = Columns.WIDTH/2 + 1;
		y = 1;
	}
	
	
	public void drawField() {
		int i, j;
		for (i = 1; i <= HEIGHT; i++) {
			for (j = 1; j <= WIDTH; j++) {
				paint.drawBox(j, i, newField.getValue(j, i));
			}
		}
	}
	
	public void drawFigure() {
		paint.drawBox(x, y, figure.data[1]);
		paint.drawBox(x, y + 1, figure.data[2]);
		paint.drawBox(x, y + 2, figure.data[3]);
	}
	
	public void dropFigure() {
		int zz;
		if (y < HEIGHT - 2) {
			zz = HEIGHT;
			while (newField.getValue(x, zz) > 0)
				zz--;
			DScore = (((level + 1) * (HEIGHT * 2 - y - zz) * 2) % 5) * 5;
			y = zz - 2;
		}
	}
	
	public boolean fullField() {
		for (int i = 1; i <= WIDTH; i++) {
			if (newField.getValue(i, 3) > 0)
				return true;
		}
		return false;
	}

	public void hideFigure() {
		paint.drawBox(x, y, 0);
		paint.drawBox(x, y + 1, 0);
		paint.drawBox(x, y + 2, 0);
	}
	
	public void packField() {
		int i, j, n;
		for (i = 1; i <= WIDTH; i++) {
			n = HEIGHT;
			for (j = HEIGHT; j > 0; j--) {
				if (oldField.getValue(i, j) > 0) {
					newField.setValue(i, n, oldField.getValue(i, j));
					n--;
				}
			}
			for (j = n; j > 0; j--)
				newField.setValue(i, j, 0);
		}
	}
	
	public void pasteFigure() {
		newField.setValue(x, y, figure.data[1]);
		newField.setValue(x, y + 1, figure.data[2]);
		newField.setValue(x, y + 2, figure.data[3]);
	}
	
	public void testField() {
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				oldField.setValue(j, i, newField.getValue(j, i));
			}
		}
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				if (newField.getValue(j, i) > 0) {
					checkNeighbours(j, i - 1, j, i + 1, i, j);
					checkNeighbours(j - 1, i, j + 1, i, i, j);
					checkNeighbours(j - 1, i - 1, j + 1, i + 1, i, j);
					checkNeighbours(j + 1, i - 1, j - 1, i + 1, i, j);
				}
			}
		}
	}
	
	
	private void checkNeighbours(int a, int b, int c, int d, int i, int j) {
		boolean first = newField.getValue(j, i) == newField.getValue(a, b);
		boolean second = newField.getValue(j, i) == newField.getValue(c, d);
		if (first && second) {
			oldField.setValue(a, b, 0);
			paint.drawBox(a, b, 8);
			oldField.setValue(j, i, 0);
			paint.drawBox(j, i, 8);
			oldField.setValue(c, d, 0);
			paint.drawBox(c, d, 8);
			noChanges = false;
			score += (level + 1) * 10;
			k++;
		}
	}
	
	
}
