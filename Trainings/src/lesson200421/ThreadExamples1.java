package lesson200421;

public class ThreadExamples1 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());

		Thread.currentThread().setName("MyThread");
		Thread.currentThread().setPriority(7);
		System.out.println(Thread.currentThread());

		new Thread(() -> System.out.println(Thread.currentThread()))
				.start();

		new Thread(ThreadExamples1::cycle).start();
	}

	private static void cycle() {
		while (true) {
			System.out.println(" hi there");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
