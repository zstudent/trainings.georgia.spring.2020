package lesson200513;

public class LambdaUsageExample {
	
	public static void main(String[] args) {
		Processor p1 = new Processor();
		Processor p2 = new SpecialProcessor();
		p1.process();
		p2.process();
		
	}
}

class Processor {
	
	final void process() {
		m1();
		m2();
		m3();
	}

	private void m1() {
		System.out.println("one");
	}

	protected void m2() {
		System.out.println("two");
	}

	private void m3() {
		System.out.println("three");
	}
	
}

class SpecialProcessor extends Processor {
	@Override
	protected void m2() {
		System.out.println("special part");
	}
}