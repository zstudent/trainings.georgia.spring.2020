import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class Tetris2 {
	public static void playSound(String soundName)
	 {
	   try 
	   {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    Clip clip = AudioSystem.getClip( );
	    clip.open(audioInputStream);
	    clip.start( );
	    	   }
	   catch(Exception ex)
	   {
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }
/** Private static Clip clip; private static Thread thread;. */

	protected static int delay = 500;

	private static final Color[] COLORS = 
		{ Color.black, Color.red, Color.green, Color.blue, Color.yellow,
				Color.cyan,Color.pink,Color.magenta,};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Tetris2::setup);
	}

	public static void setup() {
		JFrame frame = new JFrame("TETRIS");
		JPanel gamePanel = new JPanel();
		JLabel level = new JLabel("Level:");
		JLabel score = new JLabel("Score:");
//		frame.setUndecorated(true);
		frame.setResizable(false);
		gamePanel.setPreferredSize(new Dimension(303, 650));
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
			graphics.fillRect(1 + col * 30, 50 + row * 30, size, size);
		});
		controller.set(model, view);

		frame.setFocusable(true);

		frame.addKeyListener(new KeyAdapter() {
					@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
								case KeyEvent.VK_UP:
					controller.rotate();
					 playSound("rotate.wav");
					break;
								case KeyEvent.VK_DOWN:
					controller.moveDown();
					 playSound("rotate.wav");
					break;
				case KeyEvent.VK_SPACE:
					controller.dropDown();
					 playSound("space.wav");
					break;

				case KeyEvent.VK_LEFT:
					controller.moveLeft();
					 playSound("rotate.wav");
					break;

				case KeyEvent.VK_RIGHT:
					controller.moveRight();
					 playSound("rotate.wav");
					break;

				default:
					break;
				}
			}
		});

		Thread thread = new Thread(() -> {
			do {
				try {
					Thread.sleep(delay - 40*view.level);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				if(view.gameOver){
//					clip.stop();
					graphics.setColor(Color.red);
					graphics.setFont(new Font("Sylfaen", Font.BOLD, 20));
					playSound("gameover.wav");
					graphics.drawString("Game Over!", 97, 40);
					 int n = JOptionPane.showConfirmDialog(  
				                null,
				                "Try Again?" ,
				                "",
				                JOptionPane.YES_NO_OPTION);

				      if(n == JOptionPane.YES_OPTION)
				      {
				    	  Tetris.setup();
				    	  frame.dispose();
				      }
				      else
				      {
				          JOptionPane.showMessageDialog(null, "Goodbye");
				          System.exit(0);
				          }
					break;
				}
				level.setText("Level: "+ view.level);
				score.setText("Score: "+ view.score);
				SwingUtilities.invokeLater(controller::moveDown);
			} while (true);
		});
		thread.setDaemon(true);
		thread.start();
		frame.setVisible(true);

		SwingUtilities.invokeLater(() -> {
			model.refreshView();
		});
	}
}