package lesson190903;

public class TemplateMethod {
	
	public static void main(String[] args) {
		A a = new A();
		
		process(a);
		process(new B());
		
		C c = new C();
	}

	private static void process(A a) {
		a.doit();
	}

}

class B extends A {
//	@Override
//	public void doit() {
//		System.out.println("one");
//		System.out.println("two");
//		System.out.println("три");
//		System.out.println("four");
//		System.out.println("five");
//	}
	
	@Override
	void part2() {
		System.out.println("три");
	}
}

class A extends Proto {
	final public void doit() {
		part1();
		part2();
		part3();
	}

	void part2() {
		System.out.println("three");
	}

	void part1() {
		System.out.println("one");
		System.out.println("two");
	}
	
}


 class C extends Proto {
	 
 }

















