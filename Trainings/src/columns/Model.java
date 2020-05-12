package columns;

import java.awt.Color;
import java.awt.Graphics;

public class Model {
		
	static final int MAX_LEVEL = 7,
					 TimeShift = 250, 
					 FigToDrop = 33, 
					 MinTimeShift = 200, 
					 FIELD_HEIGHT = 15, FIELD_WIDTH = 7;
	
	private View view;
	private Logic logic;
	
	private int currentLevel = 0, k = 0;
	private long playerScore = 0, playerScoreAddition = 0;
	private boolean NoChanges = true;
	
	public Model(View view) {
		this.view = view;
		logic = new Logic();
	}
	
	public void gameProcess() {
		while (!gameIsOver()){
			CreateNewFigureAndStartFalling();
			checkFieldForSimplificationsWithJustLandedFigure();
		}
	}
	
	public boolean gameIsOver() {
		return logic.gameIsOver();
	}
	
	public void moveFigureLeft() {
		HideFigure();
		logic.moveFigureLeft();
		showFigure();
	}

	public void moveFigureRight() {
		HideFigure();
		logic.moveFigureRight();
		showFigure();
	}

	public void colorsMoveUp() {
		logic.colorsMoveUp();
		showFigure();
	}
		
	public void colorsMoveDown() {
		logic.colorsMoveDown();
		showFigure();
	}
	
	public void dropFigure() {
		HideFigure();
		playerScoreAddition = logic.DropFigureAndReturnAdditionalScore();
		showFigure();
	}
	
	public void pause() {
		HideFigure();
		Delay(500);
		showFigure();
		Delay(500);
	}
	
	private void HideFigure() {
		for (int i = 0; i < 3; i++) {
			view.drawBox(logic.getFigure().getColumn(), logic.getFigure().getRow() + i, 0);
		}
	}	
	
	private void showFigure() {
		view.drawFigure(logic.getFigure());
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
	
	public void updateEverythingOnView() {
		view.showLevel(currentLevel);
		view.showScore(playerScore);
		view.drawField(logic.getNewField());
		view.drawFigure(logic.getFigure());
	}
	
	private void CreateNewFigureAndStartFalling() {
		long startTime = System.currentTimeMillis();
		logic.createNewFigure();
		updateEverythingOnView();
		moveFigureAllTheWayDown(startTime);
		logic.PasteFigure(logic.getFigure());
	}
	
	private void moveFigureAllTheWayDown(long timer){
		while (logic.figureNotAtBottom() && logic.figureCanMoveDown()) {
			if ((int) (System.currentTimeMillis() - timer) > (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift) {
				timer = System.currentTimeMillis();
				HideFigure();
				logic.setFigureRow(logic.getFigureRow()+1);
				updateEverythingOnView();
			}
			playerScoreAddition = 0;
			while ((int) (System.currentTimeMillis() - timer) <= (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift);
		}
	}
	
	private void checkFieldForSimplificationsWithJustLandedFigure() {
		do {
			NoChanges = true;
			checkFieldForSameColoredBoxNeighbours();
			if (!NoChanges) {
				Delay(500);
				logic.copyOldFieldToNewField();
				playerScore += playerScoreAddition;
				if (k >= FigToDrop) {
					k = 0;
					if (currentLevel < MAX_LEVEL)
						currentLevel++;
				}
				updateEverythingOnView();
			}
		} while (!NoChanges);
	}
	
	private void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {

		}
	}

	private void checkFieldForSameColoredBoxNeighbours() {
		for (int row = 1; row <= FIELD_HEIGHT; row++) {
			for (int col = 1; col <= FIELD_WIDTH; col++) {
				logic.getOldField()[col][row] = logic.getNewField()[col][row];
			}
		}
		for (int col = 1; col <= FIELD_HEIGHT; col++) {
			for (int row = 1; row <= FIELD_WIDTH; row++) {
				if (logic.getNewField()[row][col] > 0) {
					CheckNeighbours(row, col - 1, row, col + 1, col, row);
					CheckNeighbours(row - 1, col, row + 1, col, col, row);
					CheckNeighbours(row - 1, col - 1, row + 1, col + 1, col, row);
					CheckNeighbours(row + 1, col - 1, row - 1, col + 1, col, row);
				}
			}
		}
	}
	
	private void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((logic.getNewField()[j][i] == logic.getNewField()[a][b]) && (logic.getNewField()[j][i] == logic.getNewField()[c][d])) {
			logic.getOldField()[a][b] = 0;
			view.drawBox(a, b, 8);
			logic.getOldField()[j][i] = 0;
			view.drawBox(j, i, 8);
			logic.getOldField()[c][d] = 0;
			view.drawBox(c, d, 8);
			NoChanges = false;
			playerScore += (currentLevel + 1) * 10;
			k++;
		}
	}
}	
