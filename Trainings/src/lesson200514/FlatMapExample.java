package lesson200514;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExample {
	
	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("one", "two", "three");

		 Stream<String> stream = list.stream();
		 Stream<String[]> stream2 = stream.map(w -> w.split(""));
//		 Stream<Stream<String>> stream3 = stream2.map(Arrays::stream);
		 Stream<String> stream3flat = stream2.flatMap(Arrays::stream);
		 Stream<String> distinct = stream3flat.distinct();
		 List<String> collect = distinct.collect(Collectors.toList());
		 System.out.println(collect);
		
		 System.out.println();
		 
		List<String> letters = list.stream()
		.map(w -> w.split(""))
		.flatMap(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());
		
		System.out.println(letters);
		
	}

}
