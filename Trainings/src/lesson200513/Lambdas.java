package lesson200513;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lambdas {
	
	//  Lambda expression 
	//  anonymous
	//  function
	//  referencable
	//  concise (small)

	//   () -> expression
	//   e -> expression
	//   (T e) -> expression
	//   () -> { statements;}
	//   e -> { statements;}
	//   (T e) -> { statements;}
	
	public static void main(String[] args) {
		Function<String, Integer> f =  (String s) -> s.length();
		Predicate<Bottle> theBottleIsBig = (Bottle b) -> b.size > 10;
		Supplier<Integer> s = () -> 42;
		Runnable r = () -> { System.out.println("hello");};
		Supplier<String> s2 = () -> {return "Success";};
		Function<Integer, String> f2 = (Integer i) -> { return "size = " + i;};
		Function<Integer, String> f3 = (Integer i) -> "size = " + i;
		UnaryOperator<String> f4 = (String i) -> {return "hi there";};
		UnaryOperator<String> f5 = (String i) -> "hi there";
		
		BinaryOperator<Integer> addition = (Integer x, Integer y) -> x + y;
		IntBinaryOperator addition2 = (x,y) -> x + y; // compiler infers  int type of args	
	
	}
	
	//  () -> {}
	//  () -> "Raul"
	//  () -> {return "Success";}
	//  (Integer i) -> return "size = " + i;
	//  (String i) -> {"hi there";}
	
	
}


class Bottle {
	int size;
}