package snake;

import java.util.Random;

public class Field {
	public static String score;
	int[][] data;

	public Field(int row, int col) {
	this.data = new int[row][col];
	}

}
