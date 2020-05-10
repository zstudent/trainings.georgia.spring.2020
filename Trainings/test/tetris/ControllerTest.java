package tetris;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class ControllerTest {

	@Test
	public void test() {
		Controller controller = new Controller();
		controller.set(new Model(), new View());
		assertNotNull(controller.view);
		assertNotNull(controller.model);
		assertFalse(controller.model.listeners.isEmpty());
	}
}
