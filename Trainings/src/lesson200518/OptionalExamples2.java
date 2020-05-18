package lesson200518;

import java.util.Optional;

public class OptionalExamples2 {
	
	public static void main(String[] args) {
		Person2 p = new Person2();
		p.car = Optional.of(new Car2());
		System.out.println(getInsuranceName(p));
	}

	private static String getInsuranceName(Person2 p) {
		return p.car
				.flatMap(Car2::getInsurance)
				.map(Insurance2::getName)
				.orElse("unknown");
	}
	
}

class Person2 {
	Optional<Car2> car = Optional.empty();
	Optional<Car2> getCar() { return car; }
}

class Car2 {
	Optional<Insurance2> insurance = Optional.empty();
	Optional<Insurance2> getInsurance() { return insurance;}
}

class Insurance2 {
	String name;
	String getName() {return name;}
}