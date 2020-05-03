import java.util.Arrays;

public class Field {

	int[][] data;

	public Field(int cols, int rows) {
		this.data = new int[rows][cols];
	}

	public int removeFilledRows() {
		int count = 0;
		OUTER: for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				if (data[r][c] == 0)
					continue OUTER;
			}
			for (int i = r; i > 0; i--) {
				System.arraycopy(data[i - 1], 0, data[i], 0,
						data[i].length);
			}
			count++;
			Arrays.fill(data[0], 0);
		}
		return count;
	}

}