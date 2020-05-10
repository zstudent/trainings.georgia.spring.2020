package lesson200421;

import utils.Utils;

public class ThreadStopExample3 {
	
	
	public static void main(String[] args) {

		System.out.println("start");
		
		Thread thread = new Thread(ThreadStopExample3::count);
		thread.start();
		
		Utils.pause(5000);
		
		thread.stop();
		
		System.out.println("finished main");
	}

	static volatile boolean stop = false;

	static void count() {
		System.out.println(Thread.currentThread());
		long c = 0;
		while (!stop) {
			c++;
		}
		System.out.println(c);
	}

	
}
