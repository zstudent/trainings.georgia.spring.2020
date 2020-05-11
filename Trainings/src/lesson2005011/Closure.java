package lesson2005011;

public class Closure {
	
	public static void main(String[] args) {
		
		
		int x = 20;
		
		x = x * 2;
		
		int xx = x;
		
		Runnable r = () -> {  // internal.x = external.x  --> captures VALUE
			System.out.println(xx);
		};
		
//		x = 30;  WRONG if no xx present
		
		r.run();
	}

}
