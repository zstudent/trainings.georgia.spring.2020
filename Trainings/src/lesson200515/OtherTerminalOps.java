package lesson200515;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import streams.Dish;

public class OtherTerminalOps {
	
	public static void main(String[] args) {
		List<Dish> result = Dish.menu.stream()
		.filter(Dish::isVegetarian)
		.collect(Collectors.toList());
		System.out.println(result);
		
		boolean hasVeg = Dish.menu.stream().anyMatch(Dish::isVegetarian);
		boolean allVeg = Dish.menu.stream().allMatch(Dish::isVegetarian);
		boolean noVeg = Dish.menu.stream().noneMatch(Dish::isVegetarian);
		// ||  &&   short circuiting
		
		// Monad ->  Haskell
		
		Optional<Dish> maybeDish = Dish.menu.stream()
		.filter(Dish::isVegetarian)
		.findAny();
		
		maybeDish.ifPresent(System.out::println);
		
		Optional<Dish> maybeDish2 = Dish.menu.stream()
				.filter(dish -> dish.getCalories() > 1000)
				.findFirst();
		
		maybeDish2.ifPresent(System.out::println);
		
	}

}
