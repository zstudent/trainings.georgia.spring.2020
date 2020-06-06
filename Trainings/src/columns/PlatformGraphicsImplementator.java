package columns;

import java.awt.Color;
import java.awt.Graphics;

public class PlatformGraphicsImplementator implements PlatformGraphics {

	static Color MyStyles[] = { Color.black, Color.cyan, Color.blue, Color.red,
	Color.green, Color.yellow, Color.pink, Color.magenta,
	Color.black };
	
	Graphics _graphics;
	
	public PlatformGraphicsImplementator(Graphics _gr) {
		super();
		this._graphics = _gr;
	}

	@Override
	public void drawBox(int x, int y, int c) {
		if (c == 0) {
			 _graphics.setColor(Color.black);
			 _graphics.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
			 _graphics.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
		} else if (c == 8) {
			_graphics.setColor(Color.white);
			_graphics.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 1,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 1, Columns.SL - 2, Columns.SL - 2);
			_graphics.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 2,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 2, Columns.SL - 4, Columns.SL - 4);
			_graphics.setColor(Color.black);
			_graphics.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL + 3,
					Columns.TopBorder + y * Columns.SL - Columns.SL + 3, Columns.SL - 6, Columns.SL - 6);
		} else {
			_graphics.setColor(MyStyles[c]);
			_graphics.fillRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
			_graphics.setColor(Color.black);
			_graphics.drawRect(Columns.LeftBorder + x * Columns.SL - Columns.SL,
					Columns.TopBorder + y * Columns.SL - Columns.SL, Columns.SL, Columns.SL);
		}
	}

	@Override
	public void showScore(Model model) {
		_graphics.setColor(Color.black);
		_graphics.clearRect(Columns.LeftBorder, 390, 100, 20);
		_graphics.drawString("Score: " + model.getScore(), Columns.LeftBorder, 400);
	}

	@Override
	public void showLevel(Model model) {
		_graphics.setColor(Color.black);
		_graphics.clearRect(Columns.LeftBorder + 100, 390, 100, 20);
		_graphics.drawString("Level: " + model.getLevel(), Columns.LeftBorder + 100, 400);
	}

	@Override
	public void showHelp() {
		_graphics.setColor(Color.black);
	
		_graphics.drawString(" Keys available:", 200 - Columns.LeftBorder, 102);
		_graphics.drawString("Roll Box Up:     ", 200 - Columns.LeftBorder, 118);
		_graphics.drawString("Roll Box Down:   ", 200 - Columns.LeftBorder, 128);
		_graphics.drawString("Figure Left:     ", 200 - Columns.LeftBorder, 138);
		_graphics.drawString("Figure Right:    ", 200 - Columns.LeftBorder, 148);
		_graphics.drawString("Level High/Low: +/-", 200 - Columns.LeftBorder, 158);
		_graphics.drawString("Drop Figure:   space", 200 - Columns.LeftBorder, 168);
		_graphics.drawString("Pause:           P", 200 - Columns.LeftBorder, 180);
		_graphics.drawString("Quit:     Esc or Q", 200 - Columns.LeftBorder, 190);
	}
	
}
