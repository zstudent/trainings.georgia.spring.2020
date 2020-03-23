package lesson190903;

public class ParameterObject {

	public static void main(String[] args) {
		
		String s = "Hello";
		int number = 100;
		int another = 200;
		String otherString = "Kate";
		
		LocalContext parameterObject = new LocalContext(s, number, another, otherString);
		
		process(parameterObject);
		
	}

	private static void process(LocalContext parameterObject) {
		System.out.println(parameterObject.s);
		System.out.println(parameterObject.number);
		System.out.println(parameterObject.another);
		System.out.println(parameterObject.getOtherString());
	}
	
}
