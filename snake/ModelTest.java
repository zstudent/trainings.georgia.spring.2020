package snake;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	 private Model model;

	 @Before
	 public void setUp() {
	 model = new Model();
	 }

	 @Test
	 public void testCreation() throws Exception {
	 assertNotNull(model.listeners);
	 assertNotNull(model.logic);
	 assertTrue(model.listeners.isEmpty());
	 }

	 @Test
	 public void testOnChange() throws Exception {
	 model.addListener(state -> assertNotNull(state));
	 assertFalse(model.listeners.isEmpty());
	 model.moveLeft();
	 assertNotNull(model.listeners);
	 }

}
