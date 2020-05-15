package lesson200515;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import streams.Dish;

public class IntStreams {
	
	public static void main(String[] args) {
		
		Stream<Integer> calories = Dish.menu.stream().map(Dish::getCalories); //auto-boxing
		int allCals = calories.reduce(0, Integer::sum);
		System.out.println(allCals);
		
		IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
//		Stream<Integer> boxed = intStream.boxed();
//		int sum = intStream.sum();
		OptionalInt maybeIntMax = intStream.max();
		
		IntStream.range(0, 10).forEach(System.out::println);
		
		IntStream.generate( () -> 1 ).limit(10).forEach(System.out::println);
		IntStream.iterate(0, n -> n < 10, n -> n + 1).forEach(System.out::println);
		IntStream.iterate(0, n -> n + 1).limit(10).forEach(System.out::println);
		IntStream.iterate(0, n -> n + 1).takeWhile(n -> n < 10).forEach(System.out::println);
		
	}

}
