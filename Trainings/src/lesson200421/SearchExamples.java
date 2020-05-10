package lesson200421;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;

public class SearchExamples {
	
	private static final int SIZE = 100_000_000;

	public static void main(String[] args) {
		
		System.out.println("generating...");
		
		int[] a = generate(SIZE);
		
		System.out.println("sorting...");
		Arrays.parallelSort(a);
		
		System.out.println("searching...");
		long elapsed1 = elapsed((t, u) -> plainSearch(t, u), a, 100);
		long elapsed2 = elapsed(Arrays::binarySearch, a, 100);
		
		System.out.println(elapsed1 + " " + elapsed2);
	}

	public static int plainSearch(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) 
				return i;
		}
		return -1;
	}

	public static int[] generate(int length) {
		Random r = new Random();
		int[] arr = new int[length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(SIZE);
		}
		return arr;
	}
	

	static public long elapsed(BiFunction<int[], Integer, Integer> searcher, int[] arr, int number) {

		long start = System.nanoTime();
		
		int result = searcher.apply(arr, number);
		
		long stop = System.nanoTime();
		
		return stop - start;
	}

}
