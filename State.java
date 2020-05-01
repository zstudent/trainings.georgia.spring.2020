import java.util.ArrayList;
import java.util.Random;

public class State {
	
	final int ROWS = 10;
	final int COLS = 10;
	
	public Field field;
	ArrayList <Point> data;
	
	int dx, dy;
	boolean moved;
	
	public State() {
		this.field = new Field(COLS, ROWS);
		data = new ArrayList <Point>();
		data.add(new Point(0, COLS / 2));
		field.data[0][COLS / 2] = 1;
		dx = 1;
		dy = 0;
		generateMeal();
		moved = true;
	}
	
	void generateMeal() {
		ArrayList <Point> list = new ArrayList <Point>();
		Random random = new Random();
		for (int i = 0; i < field.data.length; i++)
			for (int j = 0; j < field.data[i].length; j++)
				if (field.data[i][j] == 0)
					list.add(new Point(i, j));
		int k = random.nextInt(list.size());
		field.data[list.get(k).x][list.get(k).y] = 2;
	}
	
	boolean isSnakeFitTheField() {
		Point point = data.get(data.size() - 1);
		int height = field.data.length;
		int width = field.data[0].length;
		if (point.x >= 0 && point.x < height && point.y >= 0 && point.y < width) {
			for (int i = 0; i < data.size() - 1; i++)
				if (data.get(i).x == point.x && data.get(i).y == point.y)
					return false;
			return true;
		} 
		return false;
	}
	
	void move() {
		moved = true;
		Point point = data.get(data.size() - 1);
		if (!(point.x + dx >=  0 && point.x + dx < field.data.length && point.y + dy >= 0 && point.y + dy < field.data[0].length)) {
			data.add(new Point(point.x + dx, point.y + dy));
			return;
		}
		boolean needMeal = false;
		if (field.data[point.x + dx][point.y + dy] == 2) {
			needMeal = true;
			data.add(new Point(point.x + dx, point.y + dy));
		} else {
			for (int i = 0; i < data.size(); i++) {
				if (i + 1 != data.size())
					data.set(i, data.get(i + 1));
			}
			data.set(data.size() - 1, new Point(point.x + dx, point.y + dy));
		}
		for (int i = 0; i < field.data.length; i++)
			for (int j = 0; j < field.data[i].length; j++)
				if (field.data[i][j] == 1)
					field.data[i][j] = 0;
		for (Point pint : data)
			field.data[pint.x][pint.y] = 1;
		if (needMeal)
			generateMeal();
			
	}
	
	void turnLeft() {
		if (dy == 0 && moved) {
			dx = 0;
			dy = -1;
			moved = false;
		}
	}
	
	public void turnRight() {
		if (dy == 0 && moved) {
			dx = 0;
			dy = 1;
			moved = false;
		}
	}
	
	public void turnDown() {
		if (dx == 0 && moved) {
			dx = 1;
			dy = 0;
			moved = false;
		}
	}
	
	public void turnUp() {
		if (dx == 0 && moved) {
			dx = -1;
			dy = 0;
			moved = false;
		}
	}
}
