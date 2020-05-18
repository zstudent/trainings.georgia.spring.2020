package lesson200518;

public class OptionalExamples {
	
	public static void main(String[] args) {
		Person p = new Person();
		p.car = new Car();
		System.out.println(getInsuranceName(p));
	}
	
	static String getInsuranceName(Person person) {
		return person.getCar().getInsurance().getName();
	}

	static String getInsuranceName2(Person person) {
		if (person != null) {
			Car car = person.getCar();
			if (car != null) {
				Insurance insurance = car.getInsurance();
				if (insurance != null) {
					return insurance.getName();
				}
			}
		}
		return "unknown";
	}
	
	static String getInsuranceName3(Person person) {
		// guard condition
		if (person == null) {
			return "unknow";
		}
		Car car = person.getCar();
		if (car == null) {
			return "unknow";
		}
		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "unknow";
		}
		return insurance.getName();
		
	}
	
}


class Person {
	Car car;
	Car getCar() { return car; }
}

class Car {
	Insurance insurance;
	Insurance getInsurance() { return insurance;}
}

class Insurance {
	String name;
	String getName() {return name;}
}