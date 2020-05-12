package columns;

import java.awt.Color;
import java.awt.Graphics;

public class Model {
		
	static final int FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2, SIZE_OF_COMPONENTS = 25, MAX_LEVEL = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200, FIELD_HEIGHT = 15, FIELD_WIDTH = 7;
	
	private Figure figure;
	private View view;
	private int newField[][], oldField[][];
	private int currentLevel = 0, k = 0;
	private long playerScore = 0, playerScoreAddition;
	private boolean NoChanges = true;
	
	public Model(View view) {
		this.view = view;
		figure = new Figure();
		newField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
		oldField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
	}
	
	public boolean gameIsOver() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			if (newField[col][3] > 0)
				return true;
		}
		return false;
	}
	
	public void updateView() {
		view.showLevel(currentLevel);
		view.showScore(playerScore);
		view.drawField(newField);
		view.drawFigure(figure);
	}
	
	public void DropFigure() {
		if (figure.getRow() < FIELD_HEIGHT - 2) {
			int figureRow = FIELD_HEIGHT;
			while (newField[figure.getColumn()][figureRow] > 0)
				figureRow--;
			playerScoreAddition = (((currentLevel + 1) * (FIELD_HEIGHT * 2 - figure.getRow() - figureRow) * 2) % 5) * 5;
			figure.setRow(figureRow - 2);
		}
	}
	
	public void CreateFigureAndMoveDown() {
		long timeElapssed = System.currentTimeMillis();
		showNewFigure();
		moveFigureDown(timeElapssed);
		PasteFigure(figure);
	}
	
	public void showNewFigure() {
		figure = new Figure();
		updateView();
	}
	
	public void moveFigureDown(long timer){
		while ((figure.getRow() < FIELD_HEIGHT - 2) && (newField[figure.getColumn()][figure.getRow() + 3] == 0)) {
			if ((int) (System.currentTimeMillis() - timer) > (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift) {
				timer = System.currentTimeMillis();
				HideFigure();
				figure.setRow(figure.getRow() + 1);
				updateView();
			}
			playerScoreAddition = 0;
			while ((int) (System.currentTimeMillis() - timer) <= (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift);
		}
	}
	
	public void checkFieldForSimplificationsWithJustLandedFigure() {
		do {
			NoChanges = true;
			checkFieldForSameColoredBoxNeighbours();
			if (!NoChanges) {
				Delay(500);
				fillNewFieldWithOldField();
				playerScore += playerScoreAddition;
				if (k >= FigToDrop) {
					k = 0;
					if (currentLevel < MAX_LEVEL)
						currentLevel++;
				}
				updateView();
			}
		} while (!NoChanges);
	}
	
	public void HideFigure() {
		for (int i = 0; i < 3; i++) {
			view.drawBox(figure.getColumn(), figure.getRow() + i, 0);
		}
	}
	
	public void fillNewFieldWithOldField() {
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
		
	public void PasteFigure(Figure figure) {
		for (int i = 0; i < 3; i++) {
			newField[figure.getColumn()][figure.getRow() + i] = figure.getColorsOfFigure()[i + 1];
		}
	}	
	
	private void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {

		}
	}

	public void checkFieldForSameColoredBoxNeighbours() {
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

	public void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
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
	
	public void dropFigure() {
		HideFigure();
		DropFigure();
		view.drawFigure(figure);
	}

	public void colorsMoveDown() {
		int tmp2 = figure.getColorsOfFigure()[1];
		figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[3];
		figure.getColorsOfFigure()[3] = figure.getColorsOfFigure()[2];
		figure.getColorsOfFigure()[2] = tmp2;
		view.drawFigure(figure);
	}

	public void colorsMoveUp() {
		int tmp = figure.getColorsOfFigure()[1];
		figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[2];
		figure.getColorsOfFigure()[2] = figure.getColorsOfFigure()[3];
		figure.getColorsOfFigure()[3] = tmp;
		view.drawFigure(figure);
	}
	
	public void moveFigureRight() {
		if ((figure.getColumn() < FIELD_WIDTH) && (newField[figure.getColumn() + 1][figure.getRow() + 2] == 0)) {
			moveFigure(1);
		}
	}
	
	public void moveFigureLeft() {
		if ((figure.getColumn() > 1) && (newField[figure.getColumn() - 1][figure.getRow() + 2] == 0)) {
			moveFigure(-1);
		}
	}

	private void moveFigure(int deltaDirection) {
		HideFigure();
		figure.setColumn(figure.getColumn() + deltaDirection);
		view.drawFigure(figure);
	}
	
	public void pause() {
		HideFigure();
		Delay(500);
		view.drawFigure(figure);
		Delay(500);
	}

	public void levelDecrease() {
		if (currentLevel > 0)
			currentLevel--;
		k = 0;
		view.showLevel(currentLevel);
	}

	public void levelIncrease() {
		if (currentLevel < MAX_LEVEL)
			currentLevel++;
		k = 0;
		view.showLevel(currentLevel);		
	}
	
}	
