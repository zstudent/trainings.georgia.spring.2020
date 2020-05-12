package columns;
import java.awt.Color;
import java.awt.Graphics;

public class Paint {
	protected Graphics graphics;
	
	public Paint(Graphics graphics) {
		this.graphics = graphics;
	}
	
	public void drawBox(int x, int y, int color) {
		// width-height of box
		int WH = Columns.SL;
		int row = Columns.LEFT_BORDER + x * WH - WH;
		int col = Columns.TOP_BORDER + y * WH - WH;
		if (color == 0) {
			graphics.setColor(Color.black);
			graphics.fillRect(row, col, WH, WH);
			graphics.drawRect(row, col, WH, WH);
		} else if (color == 8) {
			graphics.setColor(Color.white);
			graphics.drawRect(row + 1, col + 1, WH - 2, WH - 2);
			graphics.drawRect(row + 2, col + 2, WH - 4, WH - 4);
			graphics.setColor(Color.black);
			graphics.fillRect(row + 3, col + 3, WH - 6, WH - 6);
		} else {
			graphics.setColor(Columns.gameColors[color]);
			graphics.fillRect(row, col, WH, WH);
			graphics.setColor(Color.black);
			graphics.drawRect(row, col, WH, WH);
		}
	}
	
	void showHelp() {
		graphics.setColor(Color.black);
		graphics.drawString(" Keys available:", 200 - Columns.LEFT_BORDER, 102);
		graphics.drawString("Roll Box Up:     ", 200 - Columns.LEFT_BORDER, 118);
		graphics.drawString("Roll Box Down:   ", 200 - Columns.LEFT_BORDER, 128);
		graphics.drawString("Figure Left:     ", 200 - Columns.LEFT_BORDER, 138);
		graphics.drawString("Figure Right:    ", 200 - Columns.LEFT_BORDER, 148);
		graphics.drawString("Level High/Low: +/-", 200 - Columns.LEFT_BORDER, 158);
		graphics.drawString("Drop Figure:   space", 200 - Columns.LEFT_BORDER, 168);
		graphics.drawString("Pause:           P", 200 - Columns.LEFT_BORDER, 180);
		graphics.drawString("Quit:     Esc or Q", 200 - Columns.LEFT_BORDER, 190);
	}
	
	void showLevel(int level) {
		graphics.setColor(Color.black);
		graphics.clearRect(Columns.LEFT_BORDER + 100, 390, 100, 20);
		graphics.drawString("Level: " + level, Columns.LEFT_BORDER + 100, 400);
	}

	void showScore(long score) {
		graphics.setColor(Color.black);
		graphics.clearRect(Columns.LEFT_BORDER, 390, 100, 20);
		graphics.drawString("Score: " + score, Columns.LEFT_BORDER, 400);
	}
	
}
