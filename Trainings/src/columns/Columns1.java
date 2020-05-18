package columns;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Columns1 {
    private static Color MyStyles[] = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
    		Color.yellow,Color.pink,Color.magenta,Color.black};
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Columns1::setup);
	}
	
	private static void setup() {
		JFrame frame = new JFrame("Columns");
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		

		Controller1 controller = new Controller1();
		
		Model1 model = new Model1();
		
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View1 view = new View1((color, row, col) -> {
			graphics.setColor(MyStyles[color]);
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
					
				default:
					break;
				}
			}
		});
		
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(500);
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
