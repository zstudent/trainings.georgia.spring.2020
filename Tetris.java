import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

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

		panel.setPreferredSize(new Dimension(1000, 1000));
		frame.add(panel);
	
		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();

		Graphics2D graphics = (Graphics2D) panel.getGraphics();
	
		View view = new View(new PlatformGraphics() {
			@Override
			public void fillRectangle(int color, int row, int col) {
				graphics.setColor(COLORS[color]);
				graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
			}
			@Override
			public void drawScoreBoard(int level, int score) {
				graphics.setColor(Color.RED);
				graphics.clearRect(50 + controller.model.logic.state.COLUMNS * 30, 0, 250, 150);
				graphics.drawString("LEVEL: " + String.valueOf(level), 50 + controller.model.logic.state.COLUMNS * 30, 50);
				graphics.drawString("SCORE: " + String.valueOf(score), 50 + controller.model.logic.state.COLUMNS * 30, 50 + 30);
				if (controller.gameOver())
					graphics.drawString("GAME OVER, LEFT-CLICK TO RESTART", 50 + controller.model.logic.state.COLUMNS * 30, 50 + 2 * 30);
			}
		});
		
		controller.set(new Model(), view);
		
		frame.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) {
                if (controller.gameOver()) {
                    controller.restart();
                }
            }
        });
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (controller.gameOver()) {
					 return;
				}
				
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
					Thread.sleep(Math.max(10, 500 - 10 * controller.model.logic.state.level));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (!controller.gameOver())
					SwingUtilities.invokeLater(controller::moveDown);
			}
		});
		
		thread.setDaemon(true);
		thread.start();

		frame.setVisible(true);
	
		SwingUtilities.invokeLater(() -> {
			controller.model.refreshView();
		});
		
	}

}