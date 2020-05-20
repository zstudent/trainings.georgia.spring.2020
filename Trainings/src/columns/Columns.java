package columns;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;

public class Columns extends Applet implements Runnable {
	static final int SL = 25, Depth = 15, Width = 7, MaxLevel = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200,
			LeftBorder = 2, TopBorder = 2;


	int ch;
	long timestamp;
	Font fCourier;
	boolean keyPressed = false;
	Graphics _gr;
	
	Model model;
	View view;

	Thread thr = null;

	void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void init() {
		_gr = getGraphics();
	}

	@Override
	public boolean keyDown(Event e, int k) {
		keyPressed = true;
		ch = e.key;
		return true;
	}

	@Override
	public boolean lostFocus(Event e, Object w) {
		keyPressed = true;
		ch = 'P';
		return true;
	}

	@Override
	public void paint(Graphics g) {
		// ShowHelp(g);

		g.setColor(Color.black);

		view.ShowLevel(model);
		view.ShowScore(model);
		view.DrawField(model);
		view.DrawFigure(model.getFig());
		requestFocus();
	}

	public void run() {
		createModelAndView();
		model.initState();
		initView();

		do {
			timestamp = System.currentTimeMillis();
			new Figure();
			view.DrawFigure(model.getFig());
			slideFigureDownUntilBottomReached();
			model.pasteFigure(model.getFig());
			do {
				removeTriples();
			} while (!model.isNoChanges());
		} while (!model.isGameOver());
	}

	private void createModelAndView() {
		model = new Model(this);
		view = new View(_gr);
	}

	private void removeTriples() {
		model.setNoChanges(true);
		model.TestField();
		if (!model.isNoChanges()) {
			Delay(500);
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

	private void slideFigureDownUntilBottomReached() {
		while (model.canFigureSlideDown()) {
			if (millisSinceLastSlideDown() > timeFrameForSlidingDown()) {
				slideFigureDown();
			}
			model.setDropScore(0);
			listenForKeyPressedUntilNextSlideDown();
		}
	}

	private void listenForKeyPressedUntilNextSlideDown() {
		do {
			Delay(50);
			if (keyPressed)
				reactToKeyAction();
		} while (millisSinceLastSlideDown() <= timeFrameForSlidingDown());
	}

	private void reactToKeyAction() {
		{
			keyPressed = false;
			switch (ch) {
			case Event.LEFT:
				if (model.canWeMoveToTheLeft()) {
					view.HideFigure(model.getFig());
					model.getFig().x--;
					view.DrawFigure(model.getFig());
				}
				break;
			case Event.RIGHT:
				if (model.canWeMoveToTheRight()) {
					view.HideFigure(model.getFig());
					model.getFig().x++;
					view.DrawFigure(model.getFig());
				}
				break;
			case Event.UP:
				model.rotateFigureColorsUp();
				view.DrawFigure(model.getFig());
				break;
			case Event.DOWN:
				model.rotateFigureColorsDown();
				view.DrawFigure(model.getFig());
				break;
			case ' ':
				view.HideFigure(model.getFig());
				model.DropFigure(model.getFig());
				view.DrawFigure(model.getFig());
				timestamp = 0;
				break;
			case 'P':
			case 'p':
				while (!keyPressed) {
					view.HideFigure(model.getFig());
					Delay(500);
					view.DrawFigure(model.getFig());
					Delay(500);
				}
				timestamp = System.currentTimeMillis();
				break;
			case '-':
				if (model.getLevel() > 0)
					model.setLevel(model.getLevel() - 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.ShowLevel(model);
				break;
			case '+':
				if (model.getLevel() < MaxLevel)
					model.setLevel(model.getLevel() + 1);
				model.setFiguresCollectedOnThisLevel(0);
				view.ShowLevel(model);
				break;
			}
		}
	}

	private void slideFigureDown() {
		timestamp = System.currentTimeMillis();
		view.HideFigure(model.getFig());
		model.getFig().y++;
		view.DrawFigure(model.getFig());
	}

	private int timeFrameForSlidingDown() {
		return (MaxLevel - model.getLevel()) * TimeShift
						+ MinTimeShift;
	}

	private int millisSinceLastSlideDown() {
		return (int) (System.currentTimeMillis()
				- timestamp);
	}

	private void initView() {
		_gr.setColor(Color.black);
		requestFocus();
	}

	@Override
	public void start() {
		_gr.setColor(Color.black);

		// setBackground (new Color(180,180,180));

		if (thr == null) {
			thr = new Thread(this);
			thr.start();
		}
	}

	@Override
	public void stop() {
		if (thr != null) {
			thr.stop();
			thr = null;
		}
	}
}