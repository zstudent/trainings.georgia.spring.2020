package snake;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StateTest {
	@Test
	public void testData() {
	State state = new State();
	assertNotNull(state.field);
	assertNotNull(state.foodPos);
	assertNotNull(state.data);
	}


}
