package lesson200513;

import java.util.Date;

public class LambdaUsageExampleCorrected {
	
	public static void main(String[] args) {
		ProcessorWithLambda p1 = new ProcessorWithLambda();
		p1.process();
		p1.setSpecial(()->System.out.println("Hi there"));
		p1.process();
		p1.setSpecial(LambdaUsageExampleCorrected::doSomeOtherStuff);
		p1.setSpecial(() -> {
			doSomeOtherStuff();
		});
		p1.process();
	}
	
	static void doSomeOtherStuff() {
		System.out.println("here it is:");
		Date date = new Date();
		System.out.println(date);
	}
}

class ProcessorWithLambda {
	
	Runnable special = this::m2;
//	Runnable special = () -> {
//		this.m2();
//	};
	
	final void process() {
		m1();
		special.run();
		m3();
	}

	public void setSpecial(Runnable task) {
		special = task;
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


