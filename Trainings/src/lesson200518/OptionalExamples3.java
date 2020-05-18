package lesson200518;

import java.util.Optional;

public class OptionalExamples3 {
	
	public static void main(String[] args) {
		
		process(null);
		System.out.println("-----------");
		process("Hi there");
		System.out.println("-----------");
		process(new Object());
		
		
	}
	
	static void process(Object o) {
		Optional<Object> optional = Optional.ofNullable(o);
		
		optional.ifPresent(System.out::println);
		optional.ifPresentOrElse(System.out::println, () -> {
			System.out.println("no value exists!");
		});
//		System.out.println(optional.orElse("nothing"));
		System.out.println(optional.orElseGet(() -> "nothing"));
		Optional<Object> filtered = optional.filter(t -> t instanceof String );
		filtered.ifPresent(t -> System.out.println(((String) t).length()));
		
	}

}
