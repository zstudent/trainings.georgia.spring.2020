package tetris;

import java.util.HashMap;
import java.util.Random;

public class Figure {
	
	
	static final int[][] I = {
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] I1 = {
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
	};
	
	static final int[][] I2 = {
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
	};
		
	
	static final int[][] T = {
			{0,2,0,0},
			{2,2,2,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T1 = {
			{0,2,0,0},
			{0,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T2 = {
			{0,0,0,0},
			{2,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T3 = {
			{0,2,0,0},
			{2,2,0,0},
			{0,2,0,0},
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
	
	static final int[][] S1 = {
			{0,4,0,0},
			{0,4,4,0},
			{0,0,4,0},
			{0,0,0,0},
	};

	static final int[][] Z = {
			{5,5,0,0},
			{0,5,5,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] Z1 = {
			{0,0,5,0},
			{0,5,5,0},
			{0,5,0,0},
			{0,0,0,0},
	};

	
	static final int[][] L = {
			{0,6,0,0},
			{0,6,0,0},
			{0,6,6,0},
			{0,0,0,0},
	};
	
	static final int[][] L1 = {
			{0,0,0,0},
			{6,6,6,0},
			{6,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] L2 = {
			{6,6,0,0},
			{0,6,0,0},
			{0,6,0,0},
			{0,0,0,0},
	};
	
	static final int[][] L3 = {
			{0,0,6,0},
			{6,6,6,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] J = {
			{0,0,7,0},
			{0,0,7,0},
			{0,7,7,0},
			{0,0,0,0},
	};
	
	static final int[][] J1 = {
			{0,7,0,0},
			{0,7,7,7},
			{0,0,0,0},
			{0,0,0,0},
	};	
	
	static final int[][] J2 = {
			{0,0,7,7},
			{0,0,7,0},
			{0,0,7,0},
			{0,0,0,0},
	};
	
	static final int[][] J3 = {
			{0,0,0,0},
			{0,7,7,7},
			{0,0,0,7},
			{0,0,0,0},
	};
	
	final HashMap<int[][], int[][]> mapRotation = new HashMap<int[][], int[][]>(){{
		put(I, I1);
		put(I1, I2);
		put(I2, I);
		put(T, T1);
		put(T1, T2);
		put(T2, T3);
		put(T3, T);
		put(O, O);
		put(Z, Z1);
		put(Z1, Z);		
		put(S, S1);
		put(S1, S);
		put(L, L1);
		put(L1, L2);		
		put(L2, L3);
		put(L3, L);
		put(J, J1);
		put(J1, J2);		
		put(J2, J3);
		put(J3, J);
	}};	
	
	static final int[][][] FIGURES = { I, T, O, S, Z, L, J };
	
	int[][] data;

	public Figure(int[][] figureData) {
		data = figureData;
	}
	
	static Random random = new Random();
	
	public Figure() {
		data = FIGURES[random.nextInt(FIGURES.length)];
	}
	
	int[][] rotatedFigure() {
		return mapRotation.get(data);
	}
	
}
