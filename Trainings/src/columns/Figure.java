package columns;
import java.applet.*;
import java.awt.*;
import java.util.*;


class Figure {
	private static int NUM_BUCKETS = 4;
	private  int column=Columns.FIELD_WIDTH/2+1;
	private  int row=1;
	private  int colorsOfFigure[]=new int[NUM_BUCKETS];
	private  Random r = new Random();
	
	public Figure()
	{
		fillFigureWithRandomColors();
	}

	private void fillFigureWithRandomColors() {
		for(int bucket =0; bucket<NUM_BUCKETS; bucket++) {
			colorsOfFigure[bucket] = (int)(Math.abs(r.nextInt())%7+1);
		}
	}
	
	public  int getColumn() {
		return column;
	}

	public  void setColumn(int column) {
		this.column = column;
	}

	public  int getRow() {
		return row;
	}

	public  void setRow(int row) {
		this.row = row;
	}

	public  int[] getColorsOfFigure() {
		return colorsOfFigure;
	}

	public  void setColorsOfFigure(int colorsOfFigure[]) {
		this.colorsOfFigure = colorsOfFigure;
	}
}
