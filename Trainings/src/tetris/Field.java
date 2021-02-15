package tetris;

import java.util.Arrays;

public class Field {

	int[][] data;
	public static int score;

	public Field(int cols, int rows) {
		this.data = new int[rows][cols];
	}

	public void removeFilledRows() {
		OUTER: for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				if (data[r][c] == 0)
					continue OUTER;
			}
			for (int i = r; i > 0; i--) {
				System.arraycopy(data[i - 1], 0, data[i], 0, data[i].length);
			}
			score += 50;
			Arrays.fill(data[0], 0);
		}

	}
}
