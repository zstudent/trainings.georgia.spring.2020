package tetris;

import java.util.Random;

public class Figure {

	static final int[][] I = { { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] T = { { 0, 2, 0, 0 }, { 2, 2, 2, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] O = { { 0, 3, 3, 0 }, { 0, 3, 3, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] S = { { 0, 4, 4, 0 }, { 4, 4, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] Z = { { 5, 5, 0, 0 }, { 0, 5, 5, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] L = { { 0, 6, 0, 0 }, { 0, 6, 0, 0 }, { 0, 6, 6, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] J = { { 0, 0, 7, 0 }, { 0, 0, 7, 0 }, { 0, 7, 7, 0 }, { 0, 0, 0, 0 }, };

	static final int[][][] FIGURES = { I, T, O, S, Z, L, J };

	static final int[][] LR = { { 0, 0, 0, 0 }, { 6, 6, 6, 0 }, { 6, 0, 0, 0 }, { 0, 0, 0, 0 } };

	static final int[][] LRR = { { 6, 6, 0, 0 }, { 0, 6, 0, 0 }, { 0, 6, 0, 0 }, { 0, 0, 0, 0 } };

	static final int[][] LRRR = { { 0, 0, 6, 0 }, { 6, 6, 6, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	static final int[][] IR = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };

	static final int[][] TR = { { 0, 2, 0, 0 }, { 0, 2, 2, 0 }, { 0, 2, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] TRR = { { 0, 0, 0, 0 }, { 2, 2, 2, 0 }, { 0, 2, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] TRRR = { { 0, 2, 0, 0 }, { 2, 2, 0, 0 }, { 0, 2, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] SR = { { 0, 4, 0, 0 }, { 0, 4, 4, 0 }, { 0, 0, 4, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] ZR = { { 0, 5, 0, 0 }, { 5, 5, 0, 0 }, { 5, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] JR = { { 0, 0, 0, 0 }, { 7, 0, 0, 0 }, { 7, 7, 7, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] JRR = { { 7, 7, 0, 0 }, { 7, 0, 0, 0 }, { 7, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	static final int[][] JRRR = { { 7, 7, 7, 0 }, { 0, 0, 7, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };

	
	

	int[][] data;

	public Figure(int[][] figureData) {
		data = figureData;
	}

	static Random random = new Random();

	public Figure() {
		data = FIGURES[random.nextInt(FIGURES.length)];
	}

	public int [] []  rotateRight() {
		if (data==I) {
			data=IR;
		}else if (data==IR) {
			data=I;
		}else if(data==T) {
			data=TR;
		}else if( data==TR) {
			data=TRR;
		}else if(data==TRR) {
			data=TRRR;
		}else if(data==S) {
			data=SR;
		}else if(data==SR) {
			data=S;
		}else if(data==Z) {
			data=ZR;
		}else if(data==ZR) {
			data=Z;
		}else if(data==J) {
			data=JR;
		}else if(data==JR) {
			data=JRR;
		}else if(data==JRR) {
			data=JRRR;
		}else if (data==JRRR) {
			data=J;
		}else if(data==L) {
			data=LR;
		}else if(data==LR) {
			data=LRR;
		}else if(data==LRR) {
			data=L;	
		}else if(data==TRRR) {
			data=T;
		}
		return data;
	}

	public int [] [] rotateLeft() {
		if(data==I) {
			data=IR;
		}else if(data==IR) {
			data=I;
		}else if (data==T) {
			data=TRRR;
		}else if (data==TRRR) {
			data=TRR;
		}else if (data==TRR) {
			data=TR;
		}else if(data==TR) {
			data=T;
		}else if(data==S) {
			data=SR;
		}else if (data==SR) {
			data=S;
		}else if (data==Z) {
			data=ZR;
		}else if(data==ZR) {
			data=Z;
		}else if(data==J) {
			data=JRRR;
		}else if(data==JRRR) {
			data=JRR;
		}else if(data==JRR) {
			data=JR;
		}else if(data==JR){
			data=J;
		}
		else if(data==L) {
			data=LRRR;
		}else if(data==LRRR) {
			data=LRR;
		}else if(data==LRR) {
			data=LR;
		}else if(data==LR) {
			data=L;
		}
		return data;
	}
	
}
