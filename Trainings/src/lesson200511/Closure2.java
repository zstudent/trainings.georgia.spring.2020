package lesson200511;

import utils.Utils;

public class Closure2 {
	
	static int y = 10;
	
	public static void main(String[] args) {
		
		System.out.println("started");
		
		int x = 0;
		
		Runnable r = () -> { 
			System.out.println("waiting..");
			Utils.pause(5000);
			System.out.println(x);
			System.out.println(Closure2.y);
		};
		
		y = 20;
		
		new Thread(r).start();
		
		System.out.println("finished");
		
	}

}
