package lesson200423;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Utils;

public class ExecutorsExamples {

	public static void main(String[] args) {
		
		System.out.println("start");
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		while (true) {
			service.execute(()->{
				Utils.pause(10);
			});
			Utils.pause(1);
		}
		
	}
	
}
