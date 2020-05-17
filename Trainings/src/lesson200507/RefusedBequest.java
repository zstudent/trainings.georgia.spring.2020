package lesson200507;

public class RefusedBequest {
	
	public static void main(String[] args) {
		R r = new R();
		S s = new S();
		
		r.m1();  r.m();
		s.m2();  s.m();
	}

}

class Q {
	void m() {	}
	void m1() {	}
	void m2() {	}
}

class R extends Q {
	@Override
	void m1() {
		super.m1();
		// something else 
	}
}

class S extends Q {
	@Override
	void m2() {
		super.m2();
		// something else 
	}
}

