package lesson200323;

import java.util.Random;

import utils.Utils;

public class DependenciesExamples {
	public static void main(String[] args) {
		while (true) {
			Client client = new Client(createWorker());
			client.process();
			Utils.pause(1000);
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