package snake;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LogicTest {
	@Test
	public void testName() throws Exception {
	Logic logic = new Logic(new State());
	assertNotNull(logic.state);
	}

}
