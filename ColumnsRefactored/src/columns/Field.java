package columns;

public class Field {
	private int [][] fieldData;
	
	public Field(int rows, int cols) {
		fieldData = new int[rows][cols];
	}
	
	public void setValue(int row, int col, int value) {
		fieldData[row][col] = value;
	}
	
	public int getValue(int row, int col) {
		return fieldData[row][col];
	}
}
