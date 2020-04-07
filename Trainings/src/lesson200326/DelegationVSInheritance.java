package lesson200326;

import java.util.Arrays;
import java.util.List;

public class DelegationVSInheritance {
	
	public static void main(String[] args) {
		List<W> workers = createWorkers();
		process(workers);
		e.set(new A());
		process(workers);
	}

	private static void process(List<W> workers) {
		workers.forEach(w -> {
			w.m();
			System.out.println(w.get());
		});
	}
	
	static E e = new E(new D(new C()));

	private static List<W> createWorkers() {
		return Arrays.asList(new A(), new B(), new C(), new D(new A()), 
				new D(new C()), e);
	}

}

class A implements W {
	int state;
	
	@Override
	public void m() { state++;	}

	@Override
	public int get() {
		return state;
	}
}

class B extends A {
	@Override
	public void m() {
		state--;
	}
}

class C implements W {
	A a = new A();
	@Override
	public void m() {
		a.m();
	}
	@Override
	public int get() {
		return -a.get();
	}
}

class D implements W {
	W w;
	public D(W w) {
		this.w = w;
	}
	@Override
	public void m() {
		w.m();
	}
	@Override
	public int get() {
		return w.get() + 10;
	}
}

class E implements W {
	W w;
	public E(W w) {
		this.w = w;
	}
	public void set(W w) {
		this.w = w;
	}
	
	@Override
	public void m() {
		w.m();
	}
	@Override
	public int get() {
		return w.get() + 100;
	}
}



