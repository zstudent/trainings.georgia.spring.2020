package lesson200421;

public class ThreadStartExamples1 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());

		Thread.currentThread().setName("MyThread");
		Thread.currentThread().setPriority(7);
		System.out.println(Thread.currentThread());

		new Thread(() -> System.out.println(Thread.currentThread()))
				.start();

		new Thread(ThreadStartExamples1::cycle).start();
	}

	private static void cycle() {
		while (true) {
			System.out.println(" hi there");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
