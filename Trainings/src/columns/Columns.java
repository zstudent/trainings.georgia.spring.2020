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
	private Model model;
	private Color colors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.white };

	public void init() {
		Graphics graphics = getGraphics();
		view = new View((int row, int col, int color) -> drawGraphics(graphics, row, col, color)
					    , (Integer currentLevel) -> drawPlayerLevel(graphics, currentLevel)
					    , (Long playerScore) -> drawPlayerScore(graphics, playerScore)
					    );
		model = new Model(view);
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
	
	public void paint(Graphics graphics) {
		model.updateView();
		requestFocus();
	}

	public void run() {
		 gameProcess();
	}
	
	public void gameProcess() {
		while (!model.gameIsOver()){
			model.CreateFigureAndMoveDown();
			model.checkFieldForSimplificationsWithJustLandedFigure();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		Thread.currentThread().stop();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			model.moveFigureLeft();
			break;
		case KeyEvent.VK_RIGHT:
			model.moveFigureRight();
			break;
		case KeyEvent.VK_UP:
			model.colorsMoveUp();
			break;
		case KeyEvent.VK_DOWN:
			model.colorsMoveDown();
			break;
		case KeyEvent.VK_SPACE:
			model.dropFigure();
			break;
		case KeyEvent.VK_P:
			model.pause();
			break;
		case KeyEvent.VK_MINUS:
			model.levelDecrease();
			break;
		case KeyEvent.VK_PLUS:
			model.levelIncrease();
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
