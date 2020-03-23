package lesson200320;

public class Liskov {
	
	public static void main(String[] args) {
		takeCare(new Cat());
		takeCare(new Dog());
		takeCare(new StrangeCat());
	}

	private static void takeCare(Mammal mammal) {
		mammal.feedWithMilk();
	}

}

class Animal {
}

abstract class Mammal extends Animal {
	abstract void feedWithMilk();
}


class Dog extends Mammal {
	@Override
	void feedWithMilk() {
		System.out.println("woof");
	}
	
}
class Cat extends Mammal {

	@Override
	void feedWithMilk() {
		System.out.println("mur-mur");
	}
	
}

class StrangeCat extends Cat {
	@Override
	void feedWithMilk() {
		throw new IllegalArgumentException();
	}
}