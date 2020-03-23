package lesson190903;

public class LocalContext {
	public String s;
	public int number;
	public int another;
	private String otherString;

	public LocalContext(String s, int number, int another, String otherString) {
		this.s = s;
		this.number = number;
		this.another = another;
		this.setOtherString(otherString);
	}

	public String getOtherString() {
		return otherString;
	}

	public void setOtherString(String otherString) {
		this.otherString = otherString;
	}
}