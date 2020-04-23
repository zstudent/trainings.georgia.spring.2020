package lesson200423;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import utils.Utils;

public class InterruptCallableExample {
	
	public static void main(String[] args) {
		
		System.out.println("start");
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> future = service.submit(()->{
			System.out.println("calculating...");
			try {
				Thread.sleep(20000);
				System.out.println("finished calculation");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return Integer.MAX_VALUE;
		});
		service.shutdown();
		

		Utils.pause(3000);
		
		future.cancel(true);
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("finish");
	}

}

