package lesson200323;

import java.util.Random;

public class DependenciesExamples {
	public static void main(String[] args) {
		while (true) {
			Client client = new Client(createWorker());
			client.process();
			pause(1000);
		}
	}

	private static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static Worker createWorker() { // Factory Method design pattern
		Random r = new Random();
		return r.nextInt() < 0 ? () -> System.out.println("hidden info") : new B();
	}

}

class A implements Worker {
	@Override
	public void doSomething() {
		System.out.println(this);
	}
}

class B extends A {

}

class Client {

	Worker a;

	public Client(Worker a) {
		this.a = a;
	}

	void process() {
		a.doSomething();
	}
}