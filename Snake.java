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

public class Snake {
	
	private static final Color[] COLORS = {Color.black, Color.blue, Color.green};
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Snake::setup);
	}
	
	private static void setup() {
		
		JFrame frame = new JFrame("Snake");

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(1000, 1000));
		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();

		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		
		View view = new View((color, row, col) -> {
			graphics.setColor(COLORS[color]);
			graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
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
					controller.turnLeft();
					break;

				case KeyEvent.VK_RIGHT:
					controller.turnRight();
					break;

				case KeyEvent.VK_DOWN:
					controller.turnDown();
					break;

				case KeyEvent.VK_UP:
					controller.turnUp();
					break;

				default:
					break;
				}
			}
		});
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (!controller.gameOver())
					SwingUtilities.invokeLater(controller::move);
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
