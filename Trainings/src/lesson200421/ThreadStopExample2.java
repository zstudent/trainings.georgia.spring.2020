package lesson200421;

import utils.Utils;

public class ThreadStopExample2 {
	
	
	public static void main(String[] args) {

		System.out.println("start");
		
		Thread thread = new Thread(ThreadStopExample2::count);
		thread.start();
		
		Utils.pause(5000);
		
		thread.interrupt();
		
		System.out.println("finished main");
	}
	
	static void count() {
		System.out.println(Thread.currentThread());
		long c = 0;
		while (!Thread.interrupted()) {
			c++;
		}
		System.out.println(c);
	}
}
