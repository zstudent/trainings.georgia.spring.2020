package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

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
		
		
		
		panel.setPreferredSize(new Dimension(400, 700));
		frame.add(panel);
		
		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Controller controller = new Controller();
		
		
		Model model = new Model();
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		
		Graphics2D graphics2 = (Graphics2D) panel.getGraphics();
		
		PlatformGraphics platform = new PlatformGraphics() {
			
			@Override
			public void fillRect(int color, int row, int col) {
				graphics.setColor(COLORS[color]);
				graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
				
			}
			
			@Override
			public void drawScore(int score, int row, int col) {
				graphics2.setColor(Color.DARK_GRAY);
				graphics2.fillRect(row+50,col+10,100,30);
				graphics2.setColor(Color.ORANGE);
				graphics2.drawString("SCORE", row+50, col+20);
				graphics2.drawString(String.valueOf(score), row+50, col+30);
			}

			@Override
			public void drawLevel(int level, int row, int col) {
				graphics2.setColor(Color.DARK_GRAY);
				graphics2.fillRect(400-row-50,col+10,100,30);
				graphics2.setColor(Color.ORANGE);
				graphics2.drawString("LEVEL", 400-row-50, col+20);
				graphics2.drawString(String.valueOf(level), 400-row-50, col+30);
				
			}

		};
		
		
		
		View view = new View(platform);
		
//		View view = new View((color, row, col) -> {
//			graphics.setColor(COLORS[color]);
//			graphics.fillRect(50 + col * 30, 50 + row * 30, 30, 30);
//		});

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
					
				case KeyEvent.VK_SPACE:
					controller.playAgain();
					break;
					
				default:
					break;
				}
			}
		});

		Thread thread = new Thread(() -> {
			while (true) {
				try {
					switch (controller.level()) {
					case 0:
						Thread.sleep(1000);						
						break;
					
					case 1:
						Thread.sleep(800);						
						break;
					
					case 2:
						Thread.sleep(600);						
						break;
						
					case 3:
						Thread.sleep(400);						
						break;
					
					case 4:
						Thread.sleep(200);						
						break;
						
					default:
						Thread.sleep(100);	
						break;
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						try {
							if(controller.gameOver()) {								
								graphics.setColor(Color.WHITE);						
								graphics.setFont(new Font("TimesRoman", Font.BOLD, 15));
								graphics.drawString("GAME OVER", 150, 200);
								graphics.drawString("If you want to play again click space", 50, 250);
							}
							else {
								controller.moveDown();							
							}
						} catch (Exception e) {
						}
						
					}
			
					
				});
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
