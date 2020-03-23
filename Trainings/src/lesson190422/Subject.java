package lesson190422;

public abstract class Subject {
	
	String data;
	
	abstract public void accept(CommandProcessor visitor);

}
