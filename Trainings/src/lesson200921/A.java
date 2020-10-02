package lesson200921;

public class A {
	
	static int w = 100;  // class field
	
	private final int x;  // instance variable, field
	
	public A(int k) {
		x = k;
	}
	
	public int getDoubled() {
		return x*2;
	}

}
