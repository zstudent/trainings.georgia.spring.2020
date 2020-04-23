package lesson200423;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import utils.Utils;

public class CallableExample {
	
	public static void main(String[] args) {
		
		System.out.println("start");
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> resultFuture = service.submit(new CalculateTask());
		service.shutdown();
		
		try {
			System.out.println("waiting for result...");
			Integer result = resultFuture.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("finish");
	}

}

class CalculateTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("calculating...");
		Utils.pause(2000);
		return Integer.MAX_VALUE;
	}
	
}
