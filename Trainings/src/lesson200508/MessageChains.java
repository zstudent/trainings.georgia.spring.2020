package lesson200508;

public class MessageChains {

	public static void main(String[] args) {
		MC3 mc3 = new MC3();
//		mc3.getMC2().getMC().mc();
		mc3.mc();
	}
	
}

class MC {
	void mc() {}
}

class MC2 {
	private MC mc = new MC();
	
	MC getMC() { return mc; }
}

class MC3 {
	private MC2 mc2 = new MC2();
	
	MC2 getMC2() { return mc2; }

	public void mc() {
		mc2.getMC().mc();
	}
}
