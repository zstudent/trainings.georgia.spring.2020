package lesson200508;

// YAGNI  You Ain't Gonna Need It

public class SpeculativeGenerality {
	public static void main(String[] args) {
		B b = new C();
		b.mB();
	}
}

abstract class A {
	abstract void mA();
}

class B extends A {
	void mB() { System.out.println("hello");	}

	@Override
	void mA() {	}
}

class C extends B {
	@Override
	void mB() { System.out.println("hi there");	}
}