package lesson200422;

public class ThreadStartExamples2 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());
		
		new MyThread().start();
	}

}

class MyThread extends Thread {
	
	@Override
	public void run() {
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