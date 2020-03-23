package lesson200320;

public class Duplicates {
	
	public static void main(String[] args) {
		AA aa = new AA();
		process(aa);
		process(new BB());
		process(new CC());
		// aa.x == ?
		System.out.println(aa.x);
		System.out.println(aa.y);
		System.out.println(aa.z);
		int i;
//		System.out.println(i); ERROR
	}

	private static void process(AA aa) {
		aa.doSomething();
	}

}

class AA {
	int x,y,z;
	public void doSomething() {
		step1();
		step2();
		step3();
	}
	private void step1() { x++; }
	protected void step2() { y++;}  
	private void step3() { z++;}
}

class CC extends AA {
	
	@Override
	protected void step2() {
		y--;
	}
	
}

class BB extends AA {
	
	@Override
	public void doSomething() {
		x++;
		y--;
		z++;
	}
}
