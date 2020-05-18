package lesson200518;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExamples4 {
	
	public static void main(String[] args) {
		
		List<Optional<? extends Object>> list = Arrays.asList(
				Optional.of("One"), 
				Optional.ofNullable(null), 
				Optional.of("two"), 
				Optional.ofNullable(null), 
				Optional.of("three")
				);
		
		list.stream()
			.flatMap(Optional::stream)
			.forEach(System.out::println);
		
	}

}
