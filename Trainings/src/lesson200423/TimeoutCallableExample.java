package lesson200423;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.Utils;

public class TimeoutCallableExample {
	
	public static void main(String[] args) {
		
		System.out.println("start");
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> resultFuture = service.submit(()->{
			System.out.println("calculating...");
			Utils.pause(2000);
			return Integer.MAX_VALUE;
		});
		service.shutdown();
		
		try {
			System.out.println("waiting for result...");
			Integer result = resultFuture.get(1, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("couldn't wait anymore, sorry");
		}
		
		System.out.println("finish");
	}

}

