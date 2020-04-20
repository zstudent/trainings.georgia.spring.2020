package tetris;

import java.util.Arrays;

public class Field {

	int[][] data;
	
	public Field(int cols, int rows) {
		this.data = new int[rows][cols];
	}

	//let removeFilledRows return number of removed rows.
	public int removeFilledRows() {
		int count = 0;
		OUTER: for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				if (data[r][c] == 0)
					continue OUTER;
			}
			count++;
			for (int i = r; i > 0; i--) {
				System.arraycopy(data[i - 1], 0, data[i], 0,
						data[i].length);
			}
			Arrays.fill(data[0], 0);
		}
		return count;
	}


	/*
		Added this for easier debugging.
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		int height = data.length;
		int width = data[0].length;
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (data[y][x]>0) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
	
}
