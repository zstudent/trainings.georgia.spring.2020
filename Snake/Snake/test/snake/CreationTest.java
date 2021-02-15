package snake;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CreationTest {

	@Test
	public void testField() {
		Field field = new Field(20, 15);
		assertNotNull(field.data);
	}

	@Test
	public void testState() {
		State state = new State();
		assertNotNull(state.field);
	}

	@Test
	public void testName() throws Exception {
		State state = new State();
		Logic logic = new Logic(state);
		assertNotNull(logic.state);
		assertEquals(logic.state, state);
	}

}
