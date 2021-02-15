package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import snake.Controller;
import snake.Field;
import snake.Model;
import snake.Snake;
import snake.View;

public class Snake {
	private static final Color[] COLORS = { Color.black, Color.yellow, Color.blue, Color.green, Color.red, Color.cyan,
			Color.magenta, Color.pink };
	private static final int VELOCITY = 1000;
	private static int velocity;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Snake::chooseLevel);
	}

	private static void chooseLevel() {
		JFrame frame = new JFrame("Snake");

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(550, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		final JButton easy = new JButton("Easy Level");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY;
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		final JButton med = new JButton("Medium Level");
		med.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY / 2;
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		final JButton hard = new JButton("Hard Level");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY / 4;
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		frame.add(easy, BorderLayout.NORTH);
		easy.setPreferredSize(new Dimension(100, 233));
		frame.add(med, BorderLayout.CENTER);
		med.setPreferredSize(new Dimension(150, 233));
		frame.add(hard, BorderLayout.SOUTH);
		hard.setPreferredSize(new Dimension(200, 233));
		frame.setVisible(true);

	}

	private static void setup() {

		JFrame frame = new JFrame("Snake");
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(550, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Controller controller = new Controller();
		Model model = new Model();
		State state = new State();
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View view = new View((int color, int row, int col) -> {
			graphics.setColor(COLORS[color]);
			graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);

		});

		controller.set(model, view);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					controller.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					controller.moveRight();
					break;
				case KeyEvent.VK_DOWN:
					controller.moveDown();
					break;
				case KeyEvent.VK_UP:
					controller.moveUp();
					break;
				}
			}
		});

		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(velocity);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (controller.gameOver()) {
					graphics.setColor(Color.RED);
					graphics.drawString("GAME OVER", 250, 350);
					break;
				}
				SwingUtilities.invokeLater(controller::moving);
			}
		});
		thread.setDaemon(true);
		thread.start();
		frame.setVisible(true);

		SwingUtilities.invokeLater(() -> {

			model.refreshView();

		});
	}
}
