package snake;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

public class DataOfSquareTest {

	@Test
	public void testDataOfSquareCreation() {
		ArrayList<Color> C =new ArrayList<Color>();
		DataOfSquare dos = new DataOfSquare(1);
		C.add(Color.darkGray);
		assertNotNull(dos);
		assertEquals(dos.color, 1);
		assertEquals(Color.darkGray, C.get(0));
	}


}
