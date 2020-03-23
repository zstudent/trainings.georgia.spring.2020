package lesson200323;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringBuilderExample {
	
	private static final int DICT_SIZE = 100_000;

	public static void main(String[] args) {
		
		String s = "hello" + ", " + " world";
		
		StringBuilder sb = new StringBuilder();
		sb.append("hello").append(", ").append("world");
		
		s = sb.toString();
		
		List<String> words = new ArrayList<>(DICT_SIZE);
		
		for (int i = 0; i < DICT_SIZE; i++) {
			words.add(generateWord());
		}
		
		for (String word : words) {
			System.out.println(word);
		}
		
		long start = System.currentTimeMillis();
		String result = joinWithConcatenation(words);
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed for concatenation: " + (stop - start));
		
		start = System.currentTimeMillis();
		result = joinWithStringBuilder(words);
		stop = System.currentTimeMillis();
		System.out.println("Elapsed for StringBuilder: " + (stop - start));
		
	}
	
	private static String joinWithStringBuilder(List<String> words) {
		StringBuilder sb = new StringBuilder(DICT_SIZE * WORD_SIZE);
		for (String word : words) {
			sb.append(word);
		}
		return sb.toString();
	}

	private static String joinWithConcatenation(List<String> words) {
		String r = "";
		for (String word : words) {
			r += word;
		}
		return r;
	}

	final static char[] ALPHABET = {'a','c','g','t'};
	final static int WORD_SIZE = 8;
	static Random r = new Random();
	
	static String generateWord() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < WORD_SIZE; i++) {
			sb.append(ALPHABET[r.nextInt(ALPHABET.length)]);
		}
		return sb.toString();
	}
	

}
