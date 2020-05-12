package columns;

import java.util.*;

class Figure {
	static int x, y, data[] = new int[4];
	private Random random;

	public Figure() {
		x = Columns.WIDTH / 2 + 1;
		y = 1;
		random = new Random();
		data[0] = 0;
		int colorsNum = Columns.gameColors.length;
		data[1] = 1 + random.nextInt(colorsNum-2);
		data[2] = 1 + random.nextInt(colorsNum-2);
		data[3] = 1 + random.nextInt(colorsNum-2);
	}
}