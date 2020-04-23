package lesson200422;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Utils;

public class UseExecutorAsWorkerFinalExample {
	
	public static void main(String[] args) {
		
		ExecutorService worker = Executors.newSingleThreadExecutor();
		
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
