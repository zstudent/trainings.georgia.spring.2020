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

public class Columns implements Runnable {
	static final int SL = 25, Depth = 15, Width = 7, MaxLevel = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200,
			LeftBorder = 2, TopBorder = 2;

	int ch;
	long timestamp;
	Font fCourier;
	boolean keyPressed = false;

	Model model;
	View view;

	Graphics _graphics;
	Thread thread;

	public static void main(String[] args) {
		Columns columns = new Columns();
		SwingUtilities.invokeLater(columns::init);
	}

	public void init() {
		
		System.out.println("start");
		
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
		
		thread = new Thread(this);
		thread.start();
		
		Thread timer = new Thread(() -> {
			while (true) {
				Utils.delay(1000);
				SwingUtilities.invokeLater(this::slideFigureDown);
			}
		});
		timer.start();

	}

	// FIXME  move to some other place
	public boolean lostFocus(Event e, Object w) {
		keyPressed = true;
		ch = 'P';
		return true;
	}

	public void paint() {
		view.drawAll(model);
	}

	public void run() {
		createModelAndView();
		model.initState();

		do {
			timestamp = System.currentTimeMillis();
			new Figure();
			view.DrawFigure(model.getFig());
			model.pasteFigure(model.getFig());
			do {
				removeTriples();
			} while (!model.isNoChanges());
		} while (!model.isGameOver());
		System.out.println("finished");
	}

	private void createModelAndView() {
		model = new Model(this);
		view = new View(_graphics);
	}

	private void removeTriples() {
		model.setNoChanges(true);
		model.TestField();
		if (!model.isNoChanges()) {
			Utils.delay(500);
			model.PackField();
			view.DrawField(model);
			model.setScore(model.getScore() + model.getDropScore());
			view.ShowScore(model);
			if (model.getFiguresCollectedOnThisLevel() >= FigToDrop) {
				model.setFiguresCollectedOnThisLevel(0);
				if (model.getLevel() < MaxLevel)
					model.setLevel(model.getLevel() + 1);
				view.ShowLevel(model);
			}
		}
	}

	private void reactToKeyAction() {
		{
			keyPressed = false;
			switch (ch) {
			case KeyEvent.VK_LEFT:
				if (model.canWeMoveToTheLeft()) {
					view.HideFigure(model.getFig());
					model.getFig().x--;
					view.DrawFigure(model.getFig());
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (model.canWeMoveToTheRight()) {
					view.HideFigure(model.getFig());
					model.getFig().x++;
					view.DrawFigure(model.getFig());
				}
				break;
			case KeyEvent.VK_UP:
				model.rotateFigureColorsUp();
				view.DrawFigure(model.getFig());
				break;
			case KeyEvent.VK_DOWN:
				model.rotateFigureColorsDown();
				view.DrawFigure(model.getFig());
				break;
			case KeyEvent.VK_SPACE:
				view.HideFigure(model.getFig());
				model.DropFigure(model.getFig());
				view.DrawFigure(model.getFig());
				timestamp = 0;
				break;
			case KeyEvent.VK_P:
				while (!keyPressed) {
					view.HideFigure(model.getFig());
					Utils.delay(500);
					view.DrawFigure(model.getFig());
					Utils.delay(500);
				}
				timestamp = System.currentTimeMillis();
				break;
			case KeyEvent.VK_MINUS:
				if (model.getLevel() > 0)
					model.setLevel(model.getLevel() - 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.ShowLevel(model);
				break;
			case KeyEvent.VK_PLUS:
				if (model.getLevel() < MaxLevel)
					model.setLevel(model.getLevel() + 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.ShowLevel(model);
				break;
			}
		}
	}

	private void slideFigureDown() {
		if (!model.canFigureSlideDown())
			return;
		timestamp = System.currentTimeMillis();
		view.HideFigure(model.getFig());
		model.getFig().y++;
		model.setDropScore(0);
		view.DrawFigure(model.getFig());
	}

	private int timeFrameForSlidingDown() {
		return (MaxLevel - model.getLevel()) * TimeShift + MinTimeShift;
	}

	private int millisSinceLastSlideDown() {
		return (int) (System.currentTimeMillis() - timestamp);
	}

}