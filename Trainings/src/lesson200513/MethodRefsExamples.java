package lesson200513;

import java.io.PrintStream;
import java.util.function.Consumer;

public class MethodRefsExamples {

	public static void main(String[] args) {
		doit(MethodRefsExamples::print);  // (1) ref to static method
		
		PrintStream out = System.out;
		doit(out::println);  // (3) ref to instance method
	}
	
	static void doit(Consumer<String> s) {
		s.accept("Hello");
	}
	
	static void print(String s) {
		System.out.println(s);
	}
	
}


