package lesson200703;

import java.util.Arrays;
import java.util.List;

public class Scope {
	
	public static void main(String[] args) {
		
		int age = 30;
		
		inscrease_age(age);
		
		System.out.println(age);
		
		String s = "hello";
		char[] charArray = s.toCharArray();
		Arrays.stream(charArray).
	
		
//		C c = new C();    ERROR!
		
	}

	private static void inscrease_age(int age) {  // local.age = global.age
		age += 5; // local.age
		System.out.println(age);
		
		Runnable r = () -> { System.out.println("hello"); };
		
		r.run();
		
		// local
		class C {
			
			void doIt() {
				System.out.println("I did it!");
			}
			
		}

		new C().doIt();
		
	}
	
	// nested
	static class A {
		
	}
	
	// inner
	class B {
		
	}

}
