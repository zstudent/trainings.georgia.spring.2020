import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Tetris {
	private static JLabel scoreLabel;
	private static JLabel countLabel;
	private static JLabel levelLabel;
	private static JLabel linesClearedLabel;
	
	private static final int DEFAULT_FALLING_SPEED = 500;
	private static final int DEFAULT_SPEED_UP = 50;
	
	private static final Color[] COLORS = 
		{ Color.black, Color.yellow, Color.blue, Color.green, Color.red, 
				Color.cyan, Color.magenta, Color.pink};


	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris::setup);
	}
	
	private static void setup() {
		JFrame frame = new JFrame("Tetris");

		JPanel panel = new JPanel();
		
		countLabel = new JLabel("Figures: " + 0);
		scoreLabel = new JLabel("score: " + 0);
		levelLabel = new JLabel("Level: " + 1);
		linesClearedLabel = new JLabel("Lines Cleared: " + 0);
		
		panel.add(countLabel);
		panel.add(linesClearedLabel);
		panel.add(scoreLabel);
		panel.add(levelLabel);
	
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
				if(controller.gameOver()) return;
				
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
					int delay = DEFAULT_FALLING_SPEED - DEFAULT_SPEED_UP*(controller.getLevel()-1);
					if(delay <= 0) delay = 50;
					Thread.sleep(delay);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(controller.gameOver()) {
					graphics.setColor(Color.BLUE);
					graphics.drawString("Game Over", 180, 40);
					break;
				}
				SwingUtilities.invokeLater(controller::moveDown);
				updateCounters(controller);
			}
		});
		
		thread.setDaemon(true);
		thread.start();

		frame.setVisible(true);
		
		SwingUtilities.invokeLater(() -> {
			model.refreshView();
		});
	}
	
	private static void updateCounters(Controller controller) {
		countLabel.setText("Figures: " + controller.countFigures());
		scoreLabel.setText("Score: " + controller.getScore());
		linesClearedLabel.setText("Lines Cleared: " + controller.linesCleared());
		levelLabel.setText("Level: " + controller.getLevel());
	}
}
