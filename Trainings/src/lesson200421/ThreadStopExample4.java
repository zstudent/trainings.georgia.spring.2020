package lesson200421;

import utils.Utils;

public class ThreadStopExample4 {
	
	
	public static void main(String[] args) {

		System.out.println("start");
		
		Thread thread = new Thread(ThreadStopExample4::count);
		thread.start();
		
		Utils.pause(5000);
		
		thread.interrupt();
		
		System.out.println("finished main");
	}
	
	static void count() {
		Thread currentThread = Thread.currentThread();
		System.out.println(currentThread);
		long c = 0;
		while (!currentThread.isInterrupted()) {
			c++;
		}
		System.out.println(c);
	}

	
}
