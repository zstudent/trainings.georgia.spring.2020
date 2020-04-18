

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Tetris {

	static protected int delay = 350;

	private static final Color[] COLORS = 
		{ Color.black, Color.yellow, Color.blue, Color.green, Color.red,
				Color.cyan, Color.magenta, Color.pink};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris::setup);
	}

	private static void setup() {
		JFrame frame = new JFrame("Tetris");
		JPanel gamePanel = new JPanel();
		JLabel score = new JLabel("Score:");
		JLabel level = new JLabel("Level:");


		gamePanel.setPreferredSize(new Dimension(400, 700));
		gamePanel.add(level);
		gamePanel.add(score);
		frame.add(gamePanel);
		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();
		
		Model model = new Model();

		Graphics2D graphics = (Graphics2D) gamePanel.getGraphics();
		View view = new View((color, row, col) -> {
			int size = 30;
			if(color != 0){
				size = 25;
			}
			graphics.setColor(COLORS[color]);
			graphics.fillRect(50 + col * 30, 50 + row * 30, size, size);
		});
		controller.set(model, view);

		frame.setFocusable(true);

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
					Thread.sleep(delay - 25*view.level);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(view.gameOver){
					graphics.setColor(Color.black);
					graphics.setFont(new Font("TimesRoman", Font.ITALIC, 20));
					graphics.drawString("Game Over", 145, 40);
					break;
				}
				score.setText("Score: "+ Integer.toString(view.score));
				level.setText("Level: "+ Integer.toString(view.level));
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
