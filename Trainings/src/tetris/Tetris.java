package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Tetris {
	private static final Color[] COLORS = { Color.black, Color.yellow, Color.blue, Color.green, Color.red, Color.cyan,
			Color.magenta, Color.pink };
	private static final int VELOCITY = 1000;
	private static int velocity;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris::chooseLevel);
	}

	private static void chooseLevel() {
		JFrame frame = new JFrame("Tetris");

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		final JButton easy = new JButton("Easy Level");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY;
				SwingUtilities.invokeLater(Tetris::setup);
			}
		});
		final JButton med = new JButton("Medium Level");
		med.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY / 2;
				SwingUtilities.invokeLater(Tetris::setup);
			}
		});
		final JButton hard = new JButton("Hard Level");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				velocity = VELOCITY / 4;
				SwingUtilities.invokeLater(Tetris::setup);
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

		JFrame frame = new JFrame("Tetris");
		JPanel panel = new JPanel();
		JLabel scoreLabel = new JLabel("SCORE: " + Field.score);
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.add(scoreLabel);
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
					controller.dropDown();
					break;
				case KeyEvent.VK_L:
					controller.rotateLeft();
					break;
				case KeyEvent.VK_R:
					controller.rotateRight();
					break;
				default:
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
					graphics.drawString("GAME OVER", 180, 350);
					break;

				}
				scoreLabel.setText("SCORE: " + Field.score);
				SwingUtilities.invokeLater(controller::moveDown);
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
