package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Tetris {
	private static final Color[] COLORS = 
		{ Color.black, Color.yellow, Color.blue, Color.green, Color.red, 
				Color.cyan, Color.magenta, Color.pink};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris::setup);
	}

	private static void setup() {
		JFrame frame = new JFrame("Tetris");
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();
		Model model = new Model();

		JLabel gameOverField = new JLabel();
		panel.add(gameOverField,BorderLayout.NORTH);
		JLabel scoreField = new JLabel();
		panel.add(scoreField,BorderLayout.NORTH);
		scoreField.setText("0");
		JButton button = new JButton("Restart");
		panel.add(button,BorderLayout.NORTH);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				controller.restartGame();
			}
		});
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View view = new View(
			(color, row, col) -> {
				graphics.setColor(COLORS[color]);
				graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
			},
			(msg)->{
				gameOverField.setText(msg);
			}, (msg)->{
				//User gets 10 points for each cleared row.
				scoreField.setText(Integer.parseInt(scoreField.getText())+(Integer.parseInt(msg)*10) + "");
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
						controller.rotateLeft();
				default:
					break;
				}
			}
		});
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(controller.timer);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
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
