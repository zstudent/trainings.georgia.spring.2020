package columns;

import java.util.Random;

public class Figure1 {
	int[][] data;
	private static Random r = new Random();
	
	
	public Figure1(int[][] colors) {
		this.data = colors;
	}
	public Figure1() {
		data = new int[3][1];
		fillWithRandomColors(data);
	}
	void fillWithRandomColors(int[][] colors) {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++)
			colors[i][j] = (int)(Math.abs(r.nextInt())%7+1);
		}
	}
}
