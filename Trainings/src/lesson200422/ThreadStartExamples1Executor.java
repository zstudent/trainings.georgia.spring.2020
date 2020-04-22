package lesson200422;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadStartExamples1Executor {
	public static void main(String[] args) {
			
		ExecutorService worker = Executors.newSingleThreadExecutor();
		
		worker.execute(() -> {
			System.out.println(Thread.currentThread());
		});
		
		worker.execute(() -> {
			while (true) {
				System.out.println(" hi there");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
