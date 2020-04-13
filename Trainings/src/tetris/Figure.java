package tetris;

import java.util.Random;

public class Figure {
	
	static final int[][] FOUR_HORIZONTAL = {
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] FOUR_VERTICAL = {
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
	};
	
	static final int[][] TRIPLE_UP = {
			{0,0,2,0},
			{0,2,2,2},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] SQUARE = {
			{0,3,3,0},
			{0,3,3,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][][] FIGURES = {
			FOUR_HORIZONTAL,
			FOUR_VERTICAL,
			TRIPLE_UP,
			SQUARE
	};
	
	int[][] data = new int[4][4];

	public Figure(int[][] figureData) {
		data = figureData;
	}
	
	static Random random = new Random();
	
	public Figure() {
		data = FIGURES[random.nextInt(FIGURES.length)];
	}
	
}
