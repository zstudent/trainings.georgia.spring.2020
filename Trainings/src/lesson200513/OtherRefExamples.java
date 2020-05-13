package lesson200513;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class OtherRefExamples {
	
	public static void main(String[] args) {
		
		// Type 1 ref
		ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
		ToIntFunction<String> stringToInt2 = Integer::parseInt;
		
		// Type 2 ref
		BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
		BiPredicate<List<String>, String> contains2 = List<String>::contains;
		
		
		// Type 3
		String t = "123num";
		Predicate<String> startsWith = (String s) -> t.startsWith(s); 
		Predicate<String> startsWith2 = t::startsWith; 
	}

}
