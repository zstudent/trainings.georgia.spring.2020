package lesson2005011;

public class PassingParameters {

	static class A {
		int value = 10;
	}
	
	public static void main(String[] args) {
		
		int x = 20;
		process(x);
		System.out.println(x);
		
		String s = "hello";
		process(s);
		System.out.println(s);

		A a = new A();
		process(a);
		System.out.println(a.value);
		
	}
	
	// ALL PARAMETERS ARE PASSED BY VALUE!

	private static void process(A other) { // other = a
		other.value = 30;
	}

	private static void process(String data) {  //  data = s 
		data = "zero";
	}

	private static void process(int param) {  // param = x
		param = 30;
	}
	
}

