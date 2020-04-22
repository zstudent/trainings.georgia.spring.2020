package lesson200422;

import utils.Utils;

public class UseWorkerThread {
	
	public static void main(String[] args) {
		
		WorkerThread3 worker = new WorkerThread3();
		new Thread(worker).start();
		
		worker.execute(() -> {
			System.out.println("one");
		});
		worker.execute(() -> {
			System.out.println("two");
		});
		
		Utils.pause(2000);
		worker.execute(() -> {
			System.out.println("three");
		});
		
	}

}
