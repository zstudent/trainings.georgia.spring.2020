package lesson200408;

import java.util.function.Consumer;

public class Cat {
	private int age;
	private int weight;
	protected int lives;

	public void feed() {
		weight++;
	}

	public void fall() {
		lives--;
	}
	
	public void timePasses() {
		age++;
	}
	
	public void change(Consumer<Cat> changer) {
		changer.accept(this);
	}
	


}
