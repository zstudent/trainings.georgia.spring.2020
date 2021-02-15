package snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State {
	private static final int COLS = 15;
	private static final int ROW = 20;
	public Field field;
	public int move;
	int[] foodPos = new int[2];
	ArrayList<Integer[]> data = new ArrayList<>();
	boolean needFood;

	public State() {
	field = new Field(ROW, COLS);
	data.add(new Integer[] { ROW / 2, 0 });
	move = 1;
	needFood = true;

	}

	public void moving() {
	if (needFood) {
	launchFood();
	needFood = false;
	}
	if (checkFood()) {
	needFood = true;
	Integer[] addFood = new Integer[] { 0, 0 };
	data.add(addFood);
	}
	for (int i = data.size() - 1; i > 0; i--) {
	Integer[] temp = new Integer[] { data.get(i - 1)[0], data.get(i - 1)[1] };
	data.set(i, temp);
	}
	rotating();
	for (int i = 0; i < field.data.length; i++) {
	for (int j = 0; j < field.data[i].length; j++) {
	if (field.data[i][j] == 1) {
	field.data[i][j] = 0;
	}
	}
	}
	for (Integer[] snake : data) {
	if (data.get(0)[0]++ < ROW && data.get(0)[1]++ < COLS && data.get(0)[0]-- > 0 && data.get(0)[1]-- > 0) {
	field.data[snake[0]][snake[1]] = 1;
	}
	}

	}

	private void rotating() {
	switch (move) {
	case 0:
	data.get(0)[0]--;
	break;
	case 1:
	data.get(0)[1]++;

	break;
	case 2:
	data.get(0)[0]++;
	break;
	case 3:
	data.get(0)[1]--;
	break;
	default:
	break;
	}
	}

	private void launchFood() {
	Random random = new Random();
	int x = random.nextInt(ROW);
	int y = random.nextInt(COLS);
	foodPos = new int[] { x, y };
	for (Integer[] snakePos : data)
	if (snakePos[0] == foodPos[0] && snakePos[1] == foodPos[1]) {
	launchFood();
	return;
	} else {
	field.data[foodPos[0]][foodPos[1]] = 2;
	return;
	}
	}

	public boolean gameOver() {
	return (data.get(0)[0] < 0 || data.get(0)[0] > ROW || data.get(0)[1] < 0 || data.get(0)[1] > COLS
	|| isColliding());
	}

	private boolean isColliding() {
	for (int i = 1; i < data.size(); i++) {
	if (data.get(i)[0] == data.get(0)[0] && data.get(i)[1] == data.get(0)[1])
	return true;
	}
	return false;
	}

	private boolean checkFood() {
	return data.get(0)[0] == foodPos[0] && data.get(0)[1] == foodPos[1];
	}
}