import java.util.HashMap;
import java.util.Random;

public class Figure {
	
	final HashMap<int[][], int[][]> rotate = new HashMap<int[][], int[][]>() {{
	    put(I, FIRST_ROTATE_I);
	    put(FIRST_ROTATE_I, I);
	    put(T, FIRST_ROTATE_T);
	    put(FIRST_ROTATE_T, SECOND_ROTATE_T);
	    put(SECOND_ROTATE_T, THIRD_ROTATE_T);
	    put(THIRD_ROTATE_T, T);
	    put(S, FIRST_ROTATE_S);
	    put(FIRST_ROTATE_S, S);
	    put(Z, FIRST_ROTATE_Z);
	    put(FIRST_ROTATE_Z, Z);
	    put(L, FIRST_ROTATE_L);
	    put(FIRST_ROTATE_L, SECOND_ROTATE_L);
	    put(SECOND_ROTATE_L, THIRD_ROTATE_L);
	    put(THIRD_ROTATE_L, L);
	    put(J, FIRST_ROTATE_J);
	    put(FIRST_ROTATE_J, SECOND_ROTATE_J);
	    put(SECOND_ROTATE_J, THIRD_ROTATE_J);
	    put(THIRD_ROTATE_J, J);
	    put(O, O);
	}};
	
	static final int[][] I = {
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] FIRST_ROTATE_I = {
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
	};
	
	static final int[][] T = {
			{0,2,0,0},
			{2,2,2,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] FIRST_ROTATE_T = {
			{0,2,0,0},
			{0,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
	};
	
	static final int[][] SECOND_ROTATE_T = {
			{0,0,0,0},
			{2,2,2,0},
			{0,2,0,0},
			{0,0,0,0},
	};
	
	static final int[][] THIRD_ROTATE_T = {
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
	
	static final int[][] FIRST_ROTATE_S = {
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
	
	static final int[][] FIRST_ROTATE_Z = {
			{0,5,0,0},
			{5,5,0,0},
			{5,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] L = {
			{0,6,0,0},
			{0,6,0,0},
			{0,6,6,0},
			{0,0,0,0},
	};
	
	static final int[][] FIRST_ROTATE_L = {
			{0,0,0,0},
			{6,6,6,0},
			{6,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] SECOND_ROTATE_L = {
			{6,6,0,0},
			{0,6,0,0},
			{0,6,0,0},
			{0,0,0,0},
	};
	
	static final int[][] THIRD_ROTATE_L = {
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
	
	static final int[][] FIRST_ROTATE_J = {
			{0,0,0,0},
			{7,0,0,0},
			{7,7,7,0},
			{0,0,0,0},
	};
	
	static final int[][] SECOND_ROTATE_J = {
			{7,7,0,0},
			{7,0,0,0},
			{7,0,0,0},
			{0,0,0,0},
	};
	
	static final int[][] THIRD_ROTATE_J = {
			{7,7,7,0},
			{0,0,7,0},
			{0,0,0,0},
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
	
	int[][] rotatedFigureData() {
		return rotate.get(data);
	}
	
}