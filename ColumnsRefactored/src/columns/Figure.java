package columns;

import java.util.*;

class Figure {
	protected int data[];
	private Random random;

	public Figure() {
		data = new int[4];
		random = new Random();
		data[0] = 0;
		int colorsNum = Columns.gameColors.length;
		data[1] = 1 + random.nextInt(colorsNum-2);
		data[2] = 1 + random.nextInt(colorsNum-2);
		data[3] = 1 + random.nextInt(colorsNum-2);
	}
}