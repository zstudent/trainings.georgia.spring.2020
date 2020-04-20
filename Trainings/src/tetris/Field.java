	package tetris;

import java.util.Arrays;

public class Field {

	int[][] data;
	int level;
	int countRemovedRows;
	
	public Field(int cols, int rows) {
		this.data = new int[rows][cols];
		this.level = 0;
		this.countRemovedRows = 0;
	}
	

	public void removeFilledRows() {
		OUTER: for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				if (data[r][c] == 0)
					continue OUTER;
			}
			for (int i = r; i > 0; i--) {
				System.arraycopy(data[i - 1], 0, data[i], 0,
						data[i].length);
			}
			countRemovedRows++;
			Arrays.fill(data[0], 0);
		}
	}
	
}
