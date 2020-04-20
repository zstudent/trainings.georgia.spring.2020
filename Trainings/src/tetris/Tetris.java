package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		JLabel level = new JLabel("Level: 0          ");
		
		JButton button=new JButton("Restart");  
	    button.setBounds(50,100,95,30);
	    		  
		JLabel score = new JLabel("         Score: 0");
		  
				
		panel.add(level);
		
		panel.add(button);
		
		button.setVisible(false);
		
		panel.add(score);

		panel.setPreferredSize(new Dimension(400, 700));
		
		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();
		
		Model model = new Model();
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (controller.gameOver()) {
					controller.restart();
					button.setVisible(false);
				}
			}			
		});

		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		View view = new View((color, row, col) -> {
			graphics.setColor(COLORS[color]);
			graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
		});

		controller.set(model, view);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!controller.gameOver()) {
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
						
					default:
						break;
					}
				}
			}
		});
			
				

		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(Math.max(10, 400 - 20 * controller.model.logic.state.field.level));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(controller.gameOver()) {
					graphics.setColor(Color.WHITE);
					graphics.setFont(new Font("", Font.BOLD, 45));
					graphics.drawString("Game Over", 55, 200);
					button.setVisible(true);
				}
				SwingUtilities.invokeLater(controller::moveDown);
				level.setText("Level: " + controller.updateLevel() + "                 ");
				score.setText("           Score:" + controller.updateScore());
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
