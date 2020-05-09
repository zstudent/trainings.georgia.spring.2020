package columns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Consumer;

public class View {
	static final int FIELD_HEIGHT = 15, FIELD_WIDTH = 7, FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2,
			GAME_NUM_OF_BOX_CONSTANT = 3;

	private DrawViewGraphics drawGraphics;
	private Consumer<Integer> drawLevel;
	private Consumer<Long> drawScore;
	
	private Color colors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.black };
	
	public View(DrawViewGraphics drawGraphics, Consumer<Integer> drawLevel, Consumer<Long> drawScore) {
		this.drawGraphics = drawGraphics;
		this.drawLevel = drawLevel;
		this.drawScore = drawScore;
	}

	public void drawBox(int column, int row, int color) {
		drawGraphics.drawRect(row, column, color);
	}
	
	public void drawField(int[][] gameFiledGrid) {
		for (int row = 1; row <= FIELD_HEIGHT; row++) {
			for (int column = 1; column <= FIELD_WIDTH; column++) {
				drawBox(column, row, gameFiledGrid[column][row]);
			}
		}
	}

	public void drawFigure(Figure figure) {
		for (int numOfBox = 0; numOfBox < GAME_NUM_OF_BOX_CONSTANT; numOfBox++) {
			drawBox(figure.getColumn(), figure.getRow() + numOfBox, figure.getColorsOfFigure()[numOfBox + 1]);
		}
	}

	void showLevel(int currentLevel) {
		drawLevel.accept(currentLevel);
	}

	void showScore(long playerScore) {
		drawScore.accept(playerScore);
	}
	
	//Not mandatory
//		 void showHelp() {
//        graphics.setColor(Color.black);
//        graphics.drawString(" Keys available:",200-FIELD_LEFT_OFFSET,102);
//        graphics.drawString("Roll Box Up:     ",200-FIELD_LEFT_OFFSET,118);
//        graphics.drawString("Roll Box Down:   ",200-FIELD_LEFT_OFFSET,128);
//        graphics.drawString("Figure Left:     ",200-FIELD_LEFT_OFFSET,138);
//        graphics.drawString("Figure Right:    ",200-FIELD_LEFT_OFFSET,148);
//        graphics.drawString("Level High/Low: +/-",200-FIELD_LEFT_OFFSET,158);
//        graphics.drawString("Drop Figure:   space",200-FIELD_LEFT_OFFSET,168);
//        graphics.drawString("Pause:           P",200-FIELD_LEFT_OFFSET,180);
//        graphics.drawString("Quit:     Esc or Q",200-FIELD_LEFT_OFFSET,190);
//    }
}
