package lesson190422;

import java.util.Arrays;
import java.util.List;

public class App {
	
	public static void main(String[] args) {
		
		List<Subject> list = Arrays.asList(new Unit(), new Product());
		Load visitor = new Load();
		
		process(list, new Store());
		process(list, visitor);
		
	}

	private static void process(List<Subject> list, CommandProcessor processor) {
		list.forEach(s -> {
			s.accept(processor);
		});
	}

}
