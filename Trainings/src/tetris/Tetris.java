package tetris;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Tetris {
	private static final Color[] COLORS = 
		{ Color.black, Color.yellow, Color.blue, Color.green, Color.red, 
				Color.cyan, Color.magenta, Color.pink};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris::setup);
	}

	private static void setup() {
		JFrame frame = new JFrame("Tetris");
		JPanel panel = new JPanel();
		frame.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JLabel gameOver = new JLabel();
		panel.add(gameOver,BorderLayout.WEST);
		JLabel score = new JLabel();
		panel.add(score,BorderLayout.EAST);
		score.setText("Score: 0");
		Font f = new Font("TimesRoman",Font.BOLD,25);
		gameOver.setForeground(Color.red);
		gameOver.setFont(f);
		Controller controller = new Controller();
		
		Model model = new Model();

		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View view = new View((color, row, col) -> {
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
				case KeyEvent.VK_UP:
					controller.rotate();
					break;
				default:
					break;
				}
			}
		});
		
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(controller.SleepTime());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.invokeLater(controller::moveDown);
				score.setText("Score:" + controller.SumScore());
				if(controller.gameOver()) {
					gameOver.setText("Game Over");
					break;
				}
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
