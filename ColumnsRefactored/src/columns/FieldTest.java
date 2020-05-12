package columns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class FieldTest {
	
	@Test
	public void testConstructor() {
		Field field = new Field(20, 20);
		assertNotNull(field);
	}
	
	
	@Test
	public void testSetAndGet() {
		Field field = new Field(20, 20);
		field.setValue(0, 0, 2);
		field.setValue(19, 19, 9);
		
		assertEquals(2, field.getValue(0, 0));
		assertEquals(9, field.getValue(19, 19));
		assertEquals(0, field.getValue(1, 1));
	}
}
