package lesson200421;

import java.util.concurrent.atomic.AtomicBoolean;

import utils.Utils;

public class ThreadStopExample5 {
	
	
	public static void main(String[] args) {

		System.out.println("start");
		
		new Thread(ThreadStopExample5::count).start();
		
		Utils.pause(5000);
		
		stop.set(true);
		
		System.out.println("finished main");
	}
	
	static AtomicBoolean stop = new AtomicBoolean(false);
	
	static void count() {
		System.out.println(Thread.currentThread());
		long c = 0;
		while (!stop.get()) {
			c++;
		}
		System.out.println(c);
	}

}
