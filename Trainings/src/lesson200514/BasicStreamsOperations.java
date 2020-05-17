package lesson200514;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import streams.*;

public class BasicStreamsOperations {
	
	public static void main(String[] args) {
		
		List<Dish> lowCals = new ArrayList<>();
		for (Dish dish : Dish.menu) {
			if (dish.getCalories() < 400) {
				lowCals.add(dish);
			}
		}
		
		Collections.sort(lowCals, (o1, o2) -> o1.getCalories() - o2.getCalories());
		
		List<String> lowCalsNames = new ArrayList<>();
		for (Dish dish : lowCals) {
			lowCalsNames.add(dish.getName());
		}
		
		System.out.println(lowCalsNames);
		
		Stream<String> finalStream = Dish.menu.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName);
		
		System.out.println("stream is created");
		
		List<String> names = finalStream
				.collect(Collectors.toList());
		
		System.out.println(names);
		
				
	}
	

}
