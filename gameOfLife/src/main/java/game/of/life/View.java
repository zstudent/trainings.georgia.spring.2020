package game.of.life;

import java.awt.Color;

import game.of.life.Cell.CellState;

public class View {
	
	private PlatformGraphics graphics;
	
	public View() {
		graphics = (color, row, col) -> {};
	}
	
	public View(PlatformGraphics graphics) {
		this.graphics = graphics;
	}
	
	public void draw(CellState[][] state) {
		for (int r = 0; r < state.length; r++) {
			for (int c = 0; c < state[0].length; c++) {
				CellState cellState = state[r][c];
				graphics.drawCell(getCellColor(cellState), r, c);
			}
		}
	}
	
	public Color getCellColor(CellState cellState) {
		switch (cellState) {
		case ALIVE:
			return Color.GREEN;
		case DEAD:
			return Color.BLACK;
			
		default:
			return Color.BLACK;
		}
		
			
	}
}
