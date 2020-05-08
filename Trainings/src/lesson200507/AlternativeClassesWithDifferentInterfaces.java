package lesson200507;

public class AlternativeClassesWithDifferentInterfaces {

}


abstract class P {
	int y;
	void m() {y++;}
	abstract void m2();
}

class G extends P {
	void m2() {y += 2;}
}

class F extends P {
	void m2() {y -= 2;}
}

// Original case
//class G {
//	int x;
//	private void mF() {x++;}
//	private void mF2() {x += 2;}
//}
//
//class F {
//	int y;
//	private void mG() {y++;}
//	private void mG2() {y -= 2;}
//}