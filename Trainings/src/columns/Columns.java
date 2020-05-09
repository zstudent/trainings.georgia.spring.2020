package columns;

import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Columns extends Applet implements Runnable, KeyListener {
	static final int FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2, SIZE_OF_COMPONENTS = 25, MAX_LEVEL = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200, FIELD_HEIGHT = 15, FIELD_WIDTH = 7;

	private View view;
	private Color colors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.white };
	
	private int currentLevel = 0, k = 0;
	private long playerScore = 0, playerScoreAddition;
	private Figure figure;
	private int newField[][], oldField[][];
	boolean NoChanges = true;
	private Thread thread = null;

	void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {

		}
	}

	void DropFigure(Figure figure) {
		if (figure.getRow() < FIELD_HEIGHT - 2) {
			int figureRow = FIELD_HEIGHT;
			while (newField[figure.getColumn()][figureRow] > 0)
				figureRow--;
			playerScoreAddition = (((currentLevel + 1) * (FIELD_HEIGHT * 2 - figure.getRow() - figureRow) * 2) % 5) * 5;
			figure.setRow(figureRow - 2);
		}
	}

	boolean isFieldFull() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			if (newField[col][3] > 0)
				return true;
		}
		return false;
	}

	void HideFigure(Figure figure) {
		for (int i = 0; i < 3; i++) {
			view.drawBox(figure.getColumn(), figure.getRow() + i, 0);
		}
	}

	public void init() {
		newField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
		oldField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
		figure = new Figure();
		Graphics graphics = getGraphics();
		view = new View((int row, int col, int color) -> {
			drawGraphics(graphics, row, col, color);
		}, (Integer currentLevel) -> {
			drawPlayerLevel(graphics, currentLevel);
		}, (Long playerScore) -> {
			drawPlayerScore(graphics, playerScore);
		});
		addKeyListener(this);
	}
	
	private void drawPlayerScore(Graphics graphics, Long playerScore) {
		graphics.setColor(Color.black);
		graphics.clearRect(FIELD_LEFT_OFFSET, 390, 100, 20);
		graphics.drawString("Score: " + playerScore, FIELD_LEFT_OFFSET, 400);
	}

	private void drawPlayerLevel(Graphics graphics, Integer currentLevel) {
		graphics.setColor(Color.black);
		graphics.clearRect(FIELD_LEFT_OFFSET + 100, 390, 100, 20);
		graphics.drawString("Level: " + currentLevel, FIELD_LEFT_OFFSET + 100, 400);
	}

	private void drawGraphics(Graphics graphics, int row, int column, int color) {
		graphics.setColor(colors[color]);
		if (color == 8) {
			for (int delta = 1; delta <= 3; delta++)
				drawBorderedRectangle(graphics, row, column, delta);
		} else {
			drawBorderedRectangle(graphics, row, column, 0);
		}
	}

	private void drawBorderedRectangle(Graphics graphics, int row, int column, int deltaCoordinate) {
		graphics.fillRect(getFigureScaledCoordinate(column) + deltaCoordinate,
				getFigureScaledCoordinate(row) + deltaCoordinate, SIZE_OF_COMPONENTS - 2 * deltaCoordinate,
				SIZE_OF_COMPONENTS - 2 * deltaCoordinate);
		graphics.setColor(Color.black);
		graphics.drawRect(getFigureScaledCoordinate(column) + deltaCoordinate,
				getFigureScaledCoordinate(row) + deltaCoordinate, SIZE_OF_COMPONENTS - 2 * deltaCoordinate,
				SIZE_OF_COMPONENTS - 2 * deltaCoordinate);
	}

	private int getFigureScaledCoordinate(int coordinate) {
		return FIELD_LEFT_OFFSET + coordinate * SIZE_OF_COMPONENTS - SIZE_OF_COMPONENTS;
	}

	void PackField() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			int n = FIELD_HEIGHT;
			for (int row = FIELD_HEIGHT; row > 0; row--) {
				if (oldField[col][row] > 0) {
					newField[col][row] = oldField[col][row];
					n--;
				}
			}
			for (int j = n; j > 0; j--)
				newField[col][j] = 0;
		}
	}

	public void paint(Graphics graphics) {
		graphics.setColor(Color.black);
		view.showLevel(currentLevel);
		view.showScore(playerScore);
		view.drawField(newField);
		view.drawFigure(figure);
		requestFocus();
	}

	void PasteFigure(Figure figure) {
		for (int i = 0; i < 3; i++) {
			newField[figure.getColumn()][figure.getRow() + i] = figure.getColorsOfFigure()[i + 1];
		}
	}

	public void run() {
		 while (!isFieldFull()){
			long tc = System.currentTimeMillis();
			figure = new Figure();
			view.drawFigure(figure);
			while ((figure.getRow() < FIELD_HEIGHT - 2) && (newField[figure.getColumn()][figure.getRow() + 3] == 0)) {
				if ((int) (System.currentTimeMillis() - tc) > (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift) {
					tc = System.currentTimeMillis();
					HideFigure(figure);
					figure.setRow(figure.getRow() + 1);
					view.drawFigure(figure);
				}
				playerScoreAddition = 0;
				while ((int) (System.currentTimeMillis() - tc) <= (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift);
			}
			PasteFigure(figure);
			do {
				NoChanges = true;
				TestField();
				if (!NoChanges) {
					Delay(500);
					PackField();
					view.drawField(newField);
					playerScore += playerScoreAddition;
					view.showScore(playerScore);
					if (k >= FigToDrop) {
						k = 0;
						if (currentLevel < MAX_LEVEL)
							currentLevel++;
						view.showLevel(currentLevel);
					}
				}
			} while (!NoChanges);
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((newField[j][i] == newField[a][b]) && (newField[j][i] == newField[c][d])) {
			oldField[a][b] = 0;
			view.drawBox(a, b, 8);
			oldField[j][i] = 0;
			view.drawBox(j, i, 8);
			oldField[c][d] = 0;
			view.drawBox(c, d, 8);
			NoChanges = false;
			playerScore += (currentLevel + 1) * 10;
			k++;
		}
	}

	void TestField() {
		for (int row = 1; row <= FIELD_HEIGHT; row++) {
			for (int col = 1; col <= FIELD_WIDTH; col++) {
				oldField[col][row] = newField[col][row];
			}
		}
		for (int col = 1; col <= FIELD_HEIGHT; col++) {
			for (int row = 1; row <= FIELD_WIDTH; row++) {
				if (newField[row][col] > 0) {
					CheckNeighbours(row, col - 1, row, col + 1, col, row);
					CheckNeighbours(row - 1, col, row + 1, col, col, row);
					CheckNeighbours(row - 1, col - 1, row + 1, col + 1, col, row);
					CheckNeighbours(row + 1, col - 1, row - 1, col + 1, col, row);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if ((figure.getColumn() > 1) && (newField[figure.getColumn() - 1][figure.getRow() + 2] == 0)) {
				HideFigure(figure);
				figure.setColumn(figure.getColumn() - 1);
				view.drawFigure(figure);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if ((figure.getColumn() < FIELD_WIDTH) && (newField[figure.getColumn() + 1][figure.getRow() + 2] == 0)) {
				HideFigure(figure);
				figure.setColumn(figure.getColumn() + 1);
				view.drawFigure(figure);
			}
			break;
		case KeyEvent.VK_UP:
			int tmp = figure.getColorsOfFigure()[1];
			figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[2];
			figure.getColorsOfFigure()[2] = figure.getColorsOfFigure()[3];
			figure.getColorsOfFigure()[3] = tmp;
			view.drawFigure(figure);
			break;
		case KeyEvent.VK_DOWN:
			int tmp2 = figure.getColorsOfFigure()[1];
			figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[3];
			figure.getColorsOfFigure()[3] = figure.getColorsOfFigure()[2];
			figure.getColorsOfFigure()[2] = tmp2;
			view.drawFigure(figure);
			break;
		case KeyEvent.VK_SPACE:
			HideFigure(figure);
			DropFigure(figure);
			view.drawFigure(figure);
			// tc = 0;
			break;
		case KeyEvent.VK_P:
			HideFigure(figure);
			Delay(500);
			view.drawFigure(figure);
			Delay(500);
			// tc = System.currentTimeMillis();
			break;
		case KeyEvent.VK_MINUS:
			if (currentLevel > 0)
				currentLevel--;
			k = 0;
			view.showLevel(currentLevel);
			break;
		case KeyEvent.VK_PLUS:
			if (currentLevel < MAX_LEVEL)
				currentLevel++;
			k = 0;
			view.showLevel(currentLevel);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		return;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		return;

	}
}
