package lesson200514;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import streams.Dish;

public class MoreExamples {
	
	public static void main(String[] args) {
		
		List<String> threeHighCalDishes = 
				Dish.menu.stream()
				.filter(d -> d.getCalories() > 400)  // intermediate op
				.map(Dish::getName)  // intermediate op
				.limit(3)   // intermediate op
				.collect(Collectors.toList()); // terminal op
		
		// select name from Dishes where calories > 400 limit 3;
		
		System.out.println(threeHighCalDishes);
		
		List<String> asList = Arrays.asList("one", "two", "three");
		
		Stream<String> stream = asList.stream();
				
		stream.forEach(System.out::println);
//		stream.forEach(System.out::println); // runtime error (exception)
		
		List<String> three = 
				Dish.menu.stream()
				.filter(d -> { 
					System.out.println(d);
					return d.getCalories() > 400; } )  // intermediate op
				.map(Dish::getName)  // intermediate op
				.limit(3)   // intermediate op
				.collect(Collectors.toList()); // terminal op
		
		long count = Dish.menu.stream()
		.filter(d -> d.getCalories() >300)
		.distinct()
		.count();
		
		System.out.println(count);
		
	}

}
