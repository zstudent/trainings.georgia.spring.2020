package columns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import columns.ModelEvent.Delay;

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

	void DrawField(int[][] field) {
		int i, j;
		for (i = 1; i <= Columns.Depth; i++) {
			for (j = 1; j <= Columns.Width; j++) {
				DrawBox(j, i, field[j][i]);
			}
		}
	}

	void DrawFigure(Model model) {
		drawFigure(model.x, model.y, model.c);
	}

	private void drawFigure(int x, int y, int[] c) {
		DrawBox(x, y, c[1]);
		DrawBox(x, y + 1, c[2]);
		DrawBox(x, y + 2, c[3]);
	}

	void ShowScore(long score) {
		_gr.setColor(Color.black);
		_gr.clearRect(Columns.LeftBorder, 390, 100, 20);
		_gr.drawString("Score: " + score, Columns.LeftBorder, 400);
	}

	void ShowLevel(int level) {
		_gr.setColor(Color.black);
		_gr.clearRect(Columns.LeftBorder + 100, 390, 100, 20);
		_gr.drawString("Level: " + level, Columns.LeftBorder + 100, 400);
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
		ShowLevel(model.getLevel());
		ShowScore(model.getScore());
		DrawField(model.newField);
		drawFigure(model.x, model.y, model.c);
	}

	public void playEvents(List<ModelEvent> events) {
		for (ModelEvent modelEvent : events) {
			modelEvent.accept(this);
		}
	}

	public void visit(ModelEvent.HideFigure hideFigure) {
		int x = hideFigure.x;
		int y = hideFigure.y;
		DrawBox(x, y, 0);
		DrawBox(x, y + 1, 0);
		DrawBox(x, y + 2, 0);
	}

	public void visit(ModelEvent.DrawFigure drawFigure) {
		drawFigure(drawFigure.x, drawFigure.y, drawFigure.c);
	}

	public void visit(Delay delay) {
		Utils.delay(delay.millis);
	}

	public void visit(columns.ModelEvent.ShowScore showScore) {
		ShowScore(showScore.score);
	}

	public void visit(columns.ModelEvent.ShowLevel showLevel) {
		ShowLevel(showLevel.level);
	}

	public void visit(columns.ModelEvent.DrawField drawField) {
		DrawField(drawField.field);
	}

}
