package lesson200422;

public class ThreadStartExamples3 {
	
	public static void main(String[] args) {
		
		long start = System.nanoTime();
		new Thread(()->{
			long stop = System.nanoTime();
			System.out.println(stop - start);
		}).start();
		
	}

}
