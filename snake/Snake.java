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

public class Snake {
	private static final Color[] COLORS = { Color.black, Color.yellow, Color.blue, Color.green, Color.red, Color.cyan,
			Color.magenta, Color.pink };
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Snake::begin);
	}

	private static void begin() {
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
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		final JButton med = new JButton("Medium Level");
		med.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		final JButton hard = new JButton("Hard Level");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SwingUtilities.invokeLater(Snake::setup);
			}
		});
		frame.add(easy,BorderLayout.NORTH);
		easy.setPreferredSize(new Dimension(100, 250));
		frame.add(med, BorderLayout.CENTER);
		med.setPreferredSize(new Dimension (150,250));
		frame.add(hard,BorderLayout.SOUTH);
		hard.setPreferredSize(new Dimension (200,250));
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

		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View view = new View((color, row, col) -> {
			graphics.setColor(COLORS[color]);
			graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
			if(controller.gameOver()) {
			}
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

				case KeyEvent.VK_UP:
					controller.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					controller.moveDown();
					break;

				default:
					break;
				}
			}
		});
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (controller.gameOver()) {
					graphics.setColor(Color.RED);
					graphics.drawString("GAME OVER", 225, 350);
					break;

				}
				SwingUtilities.invokeLater(controller::move);
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
