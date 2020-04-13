package tetris;

public class Field {

	int[][] data = new int[0][0];
	
	public Field(int cols, int rows) {
		this.data = new int[rows][cols];
	}
	
}
