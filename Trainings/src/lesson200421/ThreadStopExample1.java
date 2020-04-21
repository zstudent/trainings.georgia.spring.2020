package lesson200421;

import utils.Utils;

public class ThreadStopExample1 {
	
	
	public static void main(String[] args) {

		System.out.println("start");
		
		new Thread(ThreadStopExample1::count).start();
		
		Utils.pause(5000);
		
		stop = true;
		
		new Thread(ThreadStopExample1::count2).start();
		System.out.println("finished main");
	}
	
	volatile static boolean stop = false;
	
	static void count() {
		System.out.println(Thread.currentThread());
		long c = 0;
		while (!stop) {
			c++;
		}
		System.out.println(c);
	}

	static void count2() {
		System.out.println(Thread.currentThread());
		long start = System.currentTimeMillis();
		long c = 0;
		while (c < 19215451655L) {
			c++;
		}
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);
	}
	
}
