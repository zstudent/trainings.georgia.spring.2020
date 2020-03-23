package lesson190422;

public class Product extends Subject {
	
	String productData;

	@Override
	public void accept(CommandProcessor visitor) {
		visitor.visit(this);
	}

}
