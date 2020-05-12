package columns;

public class Logic {
	static final int FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2, SIZE_OF_COMPONENTS = 25, MAX_LEVEL = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200, FIELD_HEIGHT = 15, FIELD_WIDTH = 7;
	
	private Figure figure;
	private int newField[][], oldField[][];
	
	private int currentLevel = 0, k = 0;
	private boolean NoChanges = true;
	
	public Logic() {
		newField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
		oldField = new int[FIELD_WIDTH + 2][FIELD_HEIGHT + 2];
		figure = new Figure();
	}
	
	public Figure getFigure() {
		return figure;
	}
	
	public int[][] getNewField(){
		return newField;
	}
	
	public int[][] getOldField(){
		return oldField;
	}
	public boolean gameIsOver() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			if (newField[col][3] > 0)
				return true;
		}
		return false;
	}
	
	public void createNewFigure() {
		figure = new Figure();
		
	}
	
	public void copyOldFieldToNewField() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			int n = FIELD_HEIGHT;
			for (int row = FIELD_HEIGHT; row > 0; row--) {
				if (getOldField()[col][row] > 0) {
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
	
	public void colorsMoveDown() {
		int tmp2 = getFigure().getColorsOfFigure()[1];
		figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[3];
		figure.getColorsOfFigure()[3] = figure.getColorsOfFigure()[2];
		figure.getColorsOfFigure()[2] = tmp2;
	}
	
	public void colorsMoveUp() {
		int tmp = figure.getColorsOfFigure()[1];
		figure.getColorsOfFigure()[1] = figure.getColorsOfFigure()[2];
		figure.getColorsOfFigure()[2] = figure.getColorsOfFigure()[3];
		figure.getColorsOfFigure()[3] = tmp;
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
		figure.setColumn(figure.getColumn() + deltaDirection);
	}
	
	public int DropFigureAndReturnAdditionalScore() {
		if (getFigure().getRow() < FIELD_HEIGHT - 2) {
			int figureRow = FIELD_HEIGHT;
			while (getNewField()[getFigure().getColumn()][figureRow] > 0)
				figureRow--;
			getFigure().setRow(figureRow - 2);
			return (((currentLevel + 1) * (FIELD_HEIGHT * 2 - getFigure().getRow() - figureRow) * 2) % 5) * 5;
		}
		return 0;
	}
	
	public boolean figureNotAtBottom() {
		return getFigure().getRow() < FIELD_HEIGHT - 2;
	}
	public boolean figureCanMoveDown() {
		return getNewField()[getFigure().getColumn()][getFigure().getRow() + 3] == 0;
	}
	
	public int getFigureRow() {
		return getFigure().getRow();
	}
	
	public void setFigureRow(int row) {
		getFigure().setRow(row);
	}
}
