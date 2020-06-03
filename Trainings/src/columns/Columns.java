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
		model.launchNewFigure();

		launchTimer();

	}

	private void launchTimer() {
		Thread timer = new Thread(() -> {
			while (true) {
				Utils.delay(timeFrameForSlidingDown());
				SwingUtilities.invokeLater(() -> {
					var events = model.slideFigureDown(this);
					view.playEvents(events);
				});
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
				var events = model.reactToKeyAction(ch);
				view.playEvents(events);
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

	private void createModelAndView() {
		model = new Model(this);
		view = new View(_graphics);
	}

	private int timeFrameForSlidingDown() {
		return (MaxLevel - model.getLevel()) * TimeShift + MinTimeShift;
	}

}