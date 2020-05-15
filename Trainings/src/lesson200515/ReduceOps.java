package lesson200515;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceOps {
	
	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
//		List<Integer> numbers = Arrays.asList();
		
		int sum = numbers.stream().reduce(0, (i, j) -> i + j);
		
		System.out.println(sum);
		
		Integer product = numbers.stream().reduce(1, (i, j) -> i * j);
		
		System.out.println(product);
		
		Optional<Integer> maybeSum = numbers.stream().reduce((i, j) -> i + j);
		
		System.out.println(maybeSum.isPresent());
		
		Optional<Integer> maybeMax = numbers.stream().reduce(Integer::max);
		maybeMax.ifPresent(System.out::println);
		Optional<Integer> maybeMin = numbers.stream().reduce(Integer::min);
		maybeMin.ifPresent(System.out::println);
		
		Integer count = numbers.stream().map(i -> 1).reduce(0, (i, j) -> i + j);
		System.out.println(count);
		
		Integer count2 = numbers.stream().reduce(0, (i, j) -> i + 1);
		System.out.println(count2);
		
	}

}
