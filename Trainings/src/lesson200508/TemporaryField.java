package lesson200508;

public class TemporaryField {

}

class T {
	int i;
	
	void m1() {}
	void m2() {}
	void m3() {
		if (Math.random() < 0.01) {
			i = 10;
		}
	}
}