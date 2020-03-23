package lesson190419;

public class TemplateMethodExample {
	
	public static void main(String[] args) {
		process(new A());
		process(new B());
		process(new C());
	}

	private static void process(A a) {
		a.doWork();
	}

}


class A {
	
	int x, y, z;
	
	public void doWork() {
//		doWork(() -> { y--;});
		doWork(this::changeY);
	}

	protected final void doWork(Runnable r) {
		x++;
		r.run();
		z = x - y;
	}

	protected void changeY() {
		y--;
	}
	
}

class B extends A {
	
	@Override
	protected void changeY() {
		y++;
	}
	
	@Override
	public void doWork() {
//		doWork(()-> {y++;});
		doWork(this::changeY);
	}
}

class C extends A {
	
	@Override
	public void doWork() {
		doWork(() -> { y+=2;});
	}
}
















