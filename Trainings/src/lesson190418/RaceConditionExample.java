package lesson190418;

public class RaceConditionExample {

	public static void main(String[] args) {

		Data data = new Data();

		new Thread(() -> {

			while (true) {
				synchronized (data) {
					data.change();
				}
			}

		}).start();

		new Thread(() -> {

			while (true) {
				synchronized (data) {
					System.out.println(data.get());
				}
			}

		}).start();

	}

}

class Data {

	int x = 0;
	int y = 0;

	public void change() {
		x++;
		y--;
	}

	public int get() {
		return x + y;
	}

}
