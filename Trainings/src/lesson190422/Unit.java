package lesson190422;

public class Unit extends Subject {
	
	String unitData;

	@Override
	public void accept(CommandProcessor visitor) {
		visitor.visit(this);
	}

}
