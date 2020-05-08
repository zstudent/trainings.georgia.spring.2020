package lesson200507;

public class InsiderTrading {

}

class I {
	private int x, y, z;

	void doWork() {
		x++;
		y += 2;
		z--;
	}
}

class J {
	I i = new I();
	
	void m() {
		i.doWork();
	}
}
