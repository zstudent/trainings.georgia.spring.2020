package lesson190418;


public class Dog {
	
	private class Fed implements Brain {

		@Override
		public void feed() {
			bark();
		}

		@Override
		public void stroke() {
			wiggle();
			bark();
			brain = new Hungry();
		}

	}

	private class Hungry implements Brain {

		@Override
		public void feed() {
			wiggle();
			brain = new Fed();
		}

		@Override
		public void stroke() {
			bite(); 
		}

	}

	interface Brain {

		void feed();

		void stroke();
		
	}
	
	private Brain brain = new Hungry();
	
	public void feed() {
		brain.feed();
	}
	
	private void bite() {
		System.out.println("bites");
	}

	private void bark() {
		System.out.println("barks");
	}

	private void wiggle() {
		System.out.println("wiggles");
	}

	public void stroke() {
		brain.stroke();
	}

}
