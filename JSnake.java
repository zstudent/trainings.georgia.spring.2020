import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class JSnake extends JComponent {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	static final int HEIGHT = 20;
	static final int WIDTH = 20;
	
	public final int DELAY = 400; // mileseconds per tick
	protected boolean gameOn;	// true if we are playing
	
	JLabel scoreLabel;
	JLabel levelLabel;
	JButton startButton;
	JButton stopButton;
	protected javax.swing.Timer timer;
	
	public static State state;
	protected static int cntMoves;
	
	JSnake(int pixels){
		super();
	
		setPreferredSize(new Dimension((WIDTH * pixels),
				(HEIGHT)*pixels));
		gameOn = false;
		state = new State();
		cntMoves = 0;
		
		
		timer = new javax.swing.Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		
	}
	
	
	private void updateCounters() {
		scoreLabel.setText("Score " + state.score);
		levelLabel.setText("Level " + state.level);
	}
	
	
	private void enableButtons() {
		startButton.setEnabled(!gameOn);
		stopButton.setEnabled(gameOn);
	}
	
	
	public void startGame() {
		// cheap way to reset the board state
		state = new State();
		
		// draw the new board state once
		repaint();
		
		updateCounters();
		gameOn = true;
		timer.start();

		enableButtons();
		cntMoves = 0;
	}
	
	
	public void stopGame() {
		gameOn = false;
		timer.stop();
		enableButtons();
		cntMoves = 0;
	}
	

	public JComponent createControlPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// SCORE
		scoreLabel = new JLabel("Score 0");
		panel.add(scoreLabel);
	
		levelLabel = new JLabel("Level 1");
		panel.add(levelLabel);
		panel.add(Box.createVerticalStrut(12));
		
		// START button
		startButton = new JButton("Start");
		startButton.setFocusable(false);
		panel.add(startButton);
		startButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		// STOP button
		stopButton = new JButton("Stop");
		stopButton.setFocusable(false);
		panel.add(stopButton);
		stopButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		
		enableButtons();
		
		return panel;
	}
	
	
	// height in pixels of a block
	private final float dY() {
		return( ((float)(getHeight())) / state.getBoard().getHeight() );
	}

	
	// width in pixels of a block
	private final float dX() {
		return( ((float)(getWidth())) / state.getBoard().getWidth() );
	}
	
	
	// the x pixel coord of the left side of a block
	private final int xPixel(int x) {
		return(Math.round((x * dX())));
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
		int boardRows = state.getBoard().height;
		int boardCols = state.getBoard().width;
		
		final int dx = Math.round(dX()-2);
		final int dy = Math.round(dY()-2);
		
		for(int row=0; row <boardRows; row++) {
			for(int col=0; col <boardCols; col++) {
				if(state.getBoard().getGrid(row, col)) {
					g.fillRect(xPixel(col), xPixel(row), dx, dy);
				}
			}
		}
	}
	
	
	public void tick() {
		if(state.gameOver) stopGame();
		state.moveSnake();
		updateCounters();
		int currDelay = DELAY - 40*(state.level - 1);
		if(currDelay < 50) currDelay = 50;
		timer.setDelay(currDelay);
		repaint();
		cntMoves++;
	}
	
	
	public static JFrame createFrame(JSnake snake) {		
		JFrame frame = new JFrame("Snake Game");
		JComponent container = (JComponent)frame.getContentPane();
		container.setLayout(new BorderLayout());

		container.add(snake, BorderLayout.CENTER);
		
		JComponent controls = snake.createControlPanel();
		container.add(controls, BorderLayout.EAST);
		
		controls.add(Box.createVerticalStrut(12));
		JButton quit = new JButton("Quit");
		controls.add(quit);
		quit.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(cntMoves > 0) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						state.turnLeft();
						cntMoves=0;
						break;
					case KeyEvent.VK_RIGHT:
						state.turnRight();
						cntMoves =0;
						break;
					case KeyEvent.VK_UP:
						state.turnUp();
						cntMoves = 0;
						break;
					case KeyEvent.VK_DOWN:
						state.turnDown();
						cntMoves=0;
						break;
					}
				}
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		return frame;
	}
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		JSnake snake = new JSnake(20);
		JFrame frame = JSnake.createFrame(snake);
		
		frame.setFocusable(true);
		frame.setVisible(true);
	}
}
