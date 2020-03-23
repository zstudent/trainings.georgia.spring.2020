package lesson200320;

public class DIP {
	public static void main(String[] args) {
		Switchable lamp = new Lamp();
		Button button = new Button(lamp);
		button.push();
		button.setDevice(new Refrigerator());
		button.push();
	}

}

class Refrigerator implements Switchable {

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		
	}
	
}

class Button {
	
	
	Switchable device;
	
	public Button(Switchable device) {
		this.device = device;
	}
	
	public void setDevice(Switchable device) {
		this.device = device;
	}

	void push() {
		if (device.isOn()) device.turnOff();
		else device.turnOn();
	}
	
}


class Lamp implements Switchable {

	boolean isOn;
	
	/* (non-Javadoc)
	 * @see lesson200320.Switchable#isOn()
	 */
	@Override
	public boolean isOn() {
		return isOn;
	}

	/* (non-Javadoc)
	 * @see lesson200320.Switchable#turnOn()
	 */
	@Override
	public void turnOn() {
		isOn = true;
	}

	/* (non-Javadoc)
	 * @see lesson200320.Switchable#turnOff()
	 */
	@Override
	public void turnOff() {
		isOn = false;
	}
	
}
