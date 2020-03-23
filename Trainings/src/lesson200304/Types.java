package lesson200304;

import java.io.Serializable;

public class Types {
	
	public static void main(String[] args) {
		Object o = new Object();
		A a;
		B b;
		C c;
		
		  o = new A();
		  o = new B();
		  o = new C();
//		  c = new B();  ERROR!
		  a = (b = new C());
		
	}
	

}


class A extends Object implements Serializable {
	
}

class B extends A {
	
}

class C extends B {
	
}
