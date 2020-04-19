package tetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Figure {
	
	
	static final int[][] I = {
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] I_CLOCKWISE1 = {
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
	};
	
	
	static final int[][] T = {
			{0,2,0,0},
			{2,2,2,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T_CLOCKWISE1 = {
			{0,2,0,0},
			{0,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T_CLOCKWISE2 = {
			{2,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] T_CLOCKWISE3 = {
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
	
	static final int[][] O_CLOCKWISE1 = {
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
	
	static final int[][] S_CLOCKWISE1 = {
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
	
	static final int[][] Z_CLOCKWISE1 = {
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
	
	static final int[][] L_CLOCKWISE1 = {
			{6,6,6,0},
			{6,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] L_CLOCKWISE2 = {
			{6,6,0,0},
			{0,6,0,0},
			{0,6,0,0},
			{0,0,0,0},
	};
	
	static final int[][] L_CLOCKWISE3 = {
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
	
	static final int[][] J_CLOCKWISE1 = {
			{7,0,0,0},
			{7,7,7,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] J_CLOCKWISE2 = {
			{0,7,7,0},
			{0,7,0,0},
			{0,7,0,0},
			{0,0,0,0},
	};
	
	static final int[][] J_CLOCKWISE3 = {
			{7,7,7,0},
			{0,0,7,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final Map<int[][], int[][]> rotate_map = new HashMap<int[][], int[][]>();
	
	
	static final int[][][] FIGURES = { I, T, O, S, Z, L, J };
	
	int[][] data;

	public Figure(int[][] figureData) {
		data = figureData;
	}
	
	static Random random = new Random();
	
	public Figure() {
		rotate_map.put(I,I_CLOCKWISE1);
		rotate_map.put(I_CLOCKWISE1,I);
		rotate_map.put(T,T_CLOCKWISE1);
		rotate_map.put(T_CLOCKWISE1,T_CLOCKWISE2);
		rotate_map.put(T_CLOCKWISE2,T_CLOCKWISE3);
		rotate_map.put(T_CLOCKWISE3,T);
		rotate_map.put(S, S_CLOCKWISE1);
		rotate_map.put(S_CLOCKWISE1,S);
		rotate_map.put(Z,Z_CLOCKWISE1);
		rotate_map.put(Z_CLOCKWISE1,Z);
		rotate_map.put(L, L_CLOCKWISE1);
		rotate_map.put(L_CLOCKWISE1,L_CLOCKWISE2);
		rotate_map.put(L_CLOCKWISE2, L_CLOCKWISE3);
		rotate_map.put(L_CLOCKWISE3,L);
		rotate_map.put(J, J_CLOCKWISE1);
		rotate_map.put(J_CLOCKWISE1, J_CLOCKWISE2);
		rotate_map.put(J_CLOCKWISE2, J_CLOCKWISE3);
		rotate_map.put(J_CLOCKWISE3,J);
		rotate_map.put(O, O_CLOCKWISE1);
		rotate_map.put(O_CLOCKWISE1,O);
		data = FIGURES[random.nextInt(FIGURES.length)];
	}
	
}
