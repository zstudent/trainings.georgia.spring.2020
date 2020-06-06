package columns;

import java.awt.Color;
import java.awt.Graphics;

public class View {

	PlatformGraphics graphics;
	
	public View() {
//		graphics = (row, col, color) -> {};
	}

	public View(PlatformGraphics graphics) {
		this.graphics = graphics;
	}


	void DrawField(Model model) {
		int i, j;
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				drawBox(j, i, model.newField[j][i]);
			}
		}
	}
	
	void drawBox(int x, int y, int c) {
		graphics.drawBox(x, y, c);
	}
	
	void DrawFigure(Model model) {
		drawBox(model.x, model.y, model.c[1]);
		drawBox(model.x, model.y + 1, model.c[2]);
		drawBox(model.x, model.y + 2, model.c[3]);
	}

	void HideFigure(Model model) {
		drawBox(model.x, model.y, 0);
		drawBox(model.x, model.y + 1, 0);
		drawBox(model.x, model.y + 2, 0);
	}



	void drawAll(Model model) {
		showLevel(model);
		showScore(model);
		DrawField(model);
		DrawFigure(model);
	}

	void showScore(Model model) {
		graphics.showScore(model);
	}

	void showLevel(Model model) {
		graphics.showLevel(model);
	}

}
