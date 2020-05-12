package columns;
import java.awt.Color;
import java.awt.Graphics;

public class Paint {
	Graphics graphics;
	
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
	
}
