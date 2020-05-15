package lesson200515;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Pairs {

	public static void main(String[] args) {
		List<Integer> n1 = Arrays.asList(1, 2, 3);
		List<Integer> n2 = Arrays.asList(3, 4);

		List<int[]> collect = n1.stream()
				.flatMap(num1 -> n2.stream()
						.filter(num2 -> (num1 + num2) % 3 == 0)
						.map(num2 -> new int[] { num1, num2 }))
				.collect(toList());

		collect.forEach(a -> System.out.println(Arrays.toString(a)));
	}

}
