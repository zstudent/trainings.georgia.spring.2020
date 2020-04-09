package lesson200408;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CatFunctions {
	
	public static void feed(CatData cat) {
		cat.weight++;
	}

	public static void fall(CatData cat) {
		switch (cat.type) {
		case "Persian":
			fallPersian(cat);
			break;
		case "Siberian":
			fallSiberian(cat);
			break;

		default:
			fallCommon(cat);
			break;
		}
	}

	private static void fallCommon(CatData cat) {
		cat.lives--;
	}

	private static void fallSiberian(CatData cat) {
		cat.lives -= 3;
	}

	private static void fallPersian(CatData cat) {
		cat.lives -= 2;
	}
	
	public static void timePasses(CatData cat) {
		cat.age++;
	}
	
	public static void change(CatData cat, Consumer<CatData> changer) {
		changer.accept(cat);
	}
	
	public static void change(Supplier<CatData> sup, Consumer<CatData> changer) {
		changer.accept(sup.get());
	}
	
}
