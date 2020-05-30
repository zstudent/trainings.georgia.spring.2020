import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SPointTest {
	
	@Test
	public void testConstructors() {
		SPoint pt1 = new SPoint(1, 2);
		SPoint pt2 = new SPoint(pt1);
		
		assertEquals(pt1.row, 1);
		assertNotEquals(pt1.row, 2);
		assertEquals(pt1.col, 2);
		assertNotEquals(pt1.col, 1);
		assertEquals(pt1.row, pt2.row);
		assertEquals(pt2.col, pt1.col);
	}
	
	
	@Test
	public void testEquals() {
		SPoint pt1 = new SPoint(2, 5);
		SPoint pt2 = new SPoint(pt1);
		SPoint pt3 = new SPoint(1, 4);
		SPoint pt4 = new SPoint(2, 4);
		
		assertTrue(pt1.equals(pt1));
		assertTrue(pt1.equals(pt2));
		assertFalse(pt1.equals("2 5"));
		assertFalse(pt1.equals(pt3));
		assertFalse(pt1.equals(pt4));
	}

}