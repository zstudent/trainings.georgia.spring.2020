package columns;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Columns {
	static final int SL = 25, Depth = 15, Width = 7, MaxLevel = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200,
			LeftBorder = 2, TopBorder = 2;

	int ch;
	Font fCourier;
	boolean keyPressed = false;

	Model model;
	View view;

	Graphics _graphics;



	public static void main(String[] args) {
		Columns columns = new Columns();
		SwingUtilities.invokeLater(columns::init);
	}

	public void init() {

		System.out.println("start");

		initUI();

		createModelAndView();
		launchNewFigure();

		launchTimer();

	}

	private void launchTimer() {
		Thread timer = new Thread(() -> {
			while (true) {
				Utils.delay(timeFrameForSlidingDown());
				SwingUtilities.invokeLater(this::slideFigureDown);
			}
		});
		timer.start();
	}

	private void initUI() {
		JFrame frame = new JFrame("Columns");

		JPanel panel = new JPanel();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressed = true;
				ch = e.getKeyCode();
				reactToKeyAction();
			}
		});

		panel.setPreferredSize(new Dimension(400, 800));

		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);

		panel.requestFocus();

		_graphics = panel.getGraphics();
		_graphics.setColor(Color.black);
	}

	// FIXME move to some other place
	public boolean lostFocus(Event e, Object w) {
		keyPressed = true;
		ch = 'P';
		return true;
	}

	public void paint() {
		view.drawAll(model);
	}

	private void launchNewFigure() {
		model.createNewFigure();
		view.DrawFigure(model);
	}

	private void createModelAndView() {
		model = new Model(this);
		view = new View(new PlatformGraphicsImplementator(_graphics));
	}

	private void removeTriples() {
		model.setNoChanges(true);
		model.TestField();
		if (!model.isNoChanges()) {
			Utils.delay(500);
			model.PackField();
			view.DrawField(model);
			model.setScore(model.getScore() + model.getDropScore());
			view.showScore(model);
			if (model.getFiguresCollectedOnThisLevel() >= FigToDrop) {
				model.setFiguresCollectedOnThisLevel(0);
				if (model.getLevel() < MaxLevel)
					model.setLevel(model.getLevel() + 1);
				view.showLevel(model);
			}
		}
	}

	private void reactToKeyAction() {
		{
			keyPressed = false;
			switch (ch) {
			case KeyEvent.VK_LEFT:
				if (model.canWeMoveToTheLeft()) {
					view.HideFigure(model);
					model.x = model.x - 1;
					view.DrawFigure(model);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (model.canWeMoveToTheRight()) {
					view.HideFigure(model);
					model.x = model.x + 1;
					view.DrawFigure(model);
				}
				break;
			case KeyEvent.VK_UP:
				model.rotateFigureColorsUp();
				view.DrawFigure(model);
				break;
			case KeyEvent.VK_DOWN:
				model.rotateFigureColorsDown();
				view.DrawFigure(model);
				break;
			case KeyEvent.VK_SPACE:
				view.HideFigure(model);
				model.DropFigure();
				view.DrawFigure(model);
				break;
			case KeyEvent.VK_P:
				while (!keyPressed) {
					view.HideFigure(model);
					Utils.delay(500);
					view.DrawFigure(model);
					Utils.delay(500);
				}
				break;
			case KeyEvent.VK_MINUS:
				if (model.getLevel() > 0)
					model.setLevel(model.getLevel() - 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.showLevel(model);
				break;
			case KeyEvent.VK_PLUS:
				if (model.getLevel() < MaxLevel)
					model.setLevel(model.getLevel() + 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.showLevel(model);
				break;
			}
		}
	}

	private void slideFigureDown() {
		if (model.canSlideFigureDown()) {
			view.HideFigure(model);
			model.y++;
			model.setDropScore(0);
			view.DrawFigure(model);
		} else {
			model.pasteFigure();
			do {
				removeTriples();
			} while (!model.isNoChanges());
			if (!model.isGameOver()) {
				launchNewFigure();
			} else {
				//  TODO  game over code
			}
		}
	}

	private int timeFrameForSlidingDown() {
		return (MaxLevel - model.getLevel()) * TimeShift + MinTimeShift;
	}

}