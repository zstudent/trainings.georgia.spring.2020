package tetris;

import java.util.Random;

public class Figure {
	
	static final int[][] I = {
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T = {
			{0,0,0,0},
			{0,2,0,0},
			{2,2,2,0},
			{0,0,0,0},
	};
	
	static final int[][] O = {
			{0,0,0,0},
			{0,3,3,0},
			{0,3,3,0},
			{0,0,0,0},
	};
	
	static final int[][] S = {
			{0,0,0,0},
			{0,4,4,0},
			{4,4,0,0},
			{0,0,0,0},
	};
	
	static final int[][] Z = {
			{0,0,0,0},
			{5,5,0,0},
			{0,5,5,0},
			{0,0,0,0},
	};
	
	static final int[][] L = {
			{0,6,0,0},
			{0,6,0,0},
			{0,6,6,0},
			{0,0,0,0},
	};
	
	static final int[][] J = {
			{0,0,7,0},
			{0,0,7,0},
			{0,7,7,0},
			{0,0,0,0},
	};

	static final int[][][] FIGURES = { I, T, O, S, Z, L, J };

	int figureHeight;

	int[][] data;

	public Figure(int[][] figureData) {
		data = figureData;
		calculateFigureHeight();
	}
	
	static Random random = new Random();
	
	public Figure() {
		data = FIGURES[random.nextInt(FIGURES.length)];
		calculateFigureHeight();
	}

	public void calculateFigureHeight()
	{
		//Remember all the figures in our matrices start from row=0;
		boolean first = true;
		int startHeight = -1;
		int endHeight = -1;
		for( int r=0; r< data.length; r++)
		{
			for(int c=0; c<data[r].length; c++)
			{
				if(first && data[r][c]>0)
				{
					first = false;
					startHeight = r;
				}
				else if(data[r][c]>0)
				{
					endHeight = r;
				}
			}
		}
		//Height is number of row index + 1;
	 	this.figureHeight = endHeight - startHeight + 1;
	}

	public Figure rotateFigure()
	{
		int[][] rotatedFigure = new int[data.length][data[0].length];
		for(int r=0; r<data.length; r++)
		{
			for(int c=0; c<data[r].length; c++)
			{
				rotatedFigure[r][c] = data[c][data.length-1-r];
			}
		}
		return new Figure(rotatedFigure);
	}
}
