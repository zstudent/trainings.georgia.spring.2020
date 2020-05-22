package columns;

import java.awt.Color;
import java.awt.Graphics;

public class View {

	private static Color MyStyles[] = { Color.black, Color.cyan, Color.blue, Color.red,
			Color.green, Color.yellow, Color.pink, Color.magenta,
			Color.black };

	private Graphics _gr;

	public View(Graphics _gr) {
		this._gr = _gr;
	}

	void DrawBox(int x, int y, int c) {
		if (c == 0) {
			_gr.setColor(Color.black);
			_gr.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
			_gr.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
		} else if (c == 8) {
			_gr.setColor(Color.white);
			_gr.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 1,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 1, Columns.SL - 2, Columns.SL - 2);
			_gr.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 2,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 2, Columns.SL - 4, Columns.SL - 4);
			_gr.setColor(Color.black);
			_gr.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 3,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 3, Columns.SL - 6, Columns.SL - 6);
		} else {
			_gr.setColor(MyStyles[c]);
			_gr.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
			_gr.setColor(Color.black);
			_gr.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
		}
	}

	void DrawField(Model model) {
		int i, j;
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				DrawBox(j, i, model.getNewField()[j][i]);
			}
		}
	}

	void DrawFigure(Figure f) {
		DrawBox(f.x, f.y, f.c[1]);
		DrawBox(f.x, f.y + 1, f.c[2]);
		DrawBox(f.x, f.y + 2, f.c[3]);
	}

	void HideFigure(Figure f) {
		DrawBox(f.x, f.y, 0);
		DrawBox(f.x, f.y + 1, 0);
		DrawBox(f.x, f.y + 2, 0);
	}

	void ShowScore(Model model) {
		_gr.setColor(Color.black);
		_gr.clearRect(Columns.LeftBorder, 390, 100, 20);
		_gr.drawString("Score: " + model.getScore(), Columns.LeftBorder, 400);
	}

	void ShowLevel(Model model) {
		_gr.setColor(Color.black);
		_gr.clearRect(Columns.LeftBorder + 100, 390, 100, 20);
		_gr.drawString("Level: " + model.getLevel(), Columns.LeftBorder + 100, 400);
	}

	void ShowHelp() {
		_gr.setColor(Color.black);
	
		_gr.drawString(" Keys available:", 200 - Columns.LeftBorder, 102);
		_gr.drawString("Roll Box Up:     ", 200 - Columns.LeftBorder, 118);
		_gr.drawString("Roll Box Down:   ", 200 - Columns.LeftBorder, 128);
		_gr.drawString("Figure Left:     ", 200 - Columns.LeftBorder, 138);
		_gr.drawString("Figure Right:    ", 200 - Columns.LeftBorder, 148);
		_gr.drawString("Level High/Low: +/-", 200 - Columns.LeftBorder, 158);
		_gr.drawString("Drop Figure:   space", 200 - Columns.LeftBorder, 168);
		_gr.drawString("Pause:           P", 200 - Columns.LeftBorder, 180);
		_gr.drawString("Quit:     Esc or Q", 200 - Columns.LeftBorder, 190);
	}

	void drawAll(Model model) {
		ShowLevel(model);
		ShowScore(model);
		DrawField(model);
		DrawFigure(model.getFig());
	}

}
