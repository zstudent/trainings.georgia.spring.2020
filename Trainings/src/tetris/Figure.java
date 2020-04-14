package tetris;

import java.util.Random;

public class Figure {
	
	static final int[][] I = {
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T = {
			{0,2,0,0},
			{2,2,2,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] O = {
			{0,3,3,0},
			{0,3,3,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] S = {
			{0,4,4,0},
			{4,4,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] Z = {
			{5,5,0,0},
			{0,5,5,0},
			{0,0,0,0},
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
	
	int[][] data;

	public Figure(int[][] figureData) {
		data = figureData;
	}
	
	static Random random = new Random();
	
	public Figure() {
		data = FIGURES[random.nextInt(FIGURES.length)];
	}
	
}
