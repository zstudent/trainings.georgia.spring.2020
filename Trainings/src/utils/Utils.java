package utils;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Utils {
	public static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
