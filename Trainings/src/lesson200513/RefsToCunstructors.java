package lesson200513;

import java.util.function.Supplier;

public class RefsToCunstructors {
	
	static Supplier<String> supplier;
	
	public static void main(String[] args) {
		
		supplier = () -> Integer.toString(42);
		System.out.println(produce());
		supplier = () -> new String("hello");
		System.out.println(produce());
		supplier = String::new;
		System.out.println(produce());
		
	}
	
	static String produce() {
		return supplier.get();
	}

}
