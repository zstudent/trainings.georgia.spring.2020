package game.of.life;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import game.of.life.Cell.CellState;

public class GameOfLife {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(GameOfLife::setup);
	}
	
	public static CellState[][] generateRandomLife() {
		CellState[][] matrix = new CellState[50][50];
		for (int r = 0; r < matrix.length; r++) {
			for (int l = 0; l < matrix[0].length; l++) {
				int pick = new Random().nextInt(CellState.values().length);
				matrix[r][l] = CellState.values()[pick];				
			}
		}
		return matrix;
	}
	
	public static void setup() {
		JFrame frame = new JFrame("GameOfLife");
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(1000, 1000));
		frame.add(panel);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Model model = new Model(generateRandomLife());
		
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		
		View view = new View((color, row, col) -> {
			graphics.setColor(color);
			graphics.fillRect(col * 20, row * 20, 20, 20);
		});
		
		Controller controller = new Controller();
		controller.set(model, view);
		
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.invokeLater(model::update);
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
