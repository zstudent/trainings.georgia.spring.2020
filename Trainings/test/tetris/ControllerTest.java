package tetris;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;

public class ControllerTest {

	private Optional<Boolean> r;

	@Test
	public void test() {
		Controller controller = new Controller();
		controller.set(new Model(), new View());
		assertNotNull(controller.view);
		assertNotNull(controller.model);
		assertFalse(controller.model.listeners.isEmpty());
	}
	
	@Test
	public void testModelCommunication() throws Exception {
		Controller c = new Controller();
		r = Optional.ofNullable(null);
		c.set(new Model() {
			@Override
			public void moveLeft() {
				r = Optional.of(true);
			}
		}, new View());
		c.moveLeft();
		if (r.isEmpty()) {
			fail();
		}
	}
	
	@Test
	public void testWithMockito() throws Exception {
		Controller c = new Controller();
		Model model = Mockito.mock(Model.class);
		View view = Mockito.mock(View.class);
		c.set(model, view);
		
		c.moveLeft();
		c.moveRight();
		c.moveDown();
		c.dropDown();
		
		Mockito.verify(model, Mockito.atLeast(1)).moveLeft();
		Mockito.verify(model, Mockito.atLeast(1)).moveRight();
		Mockito.verify(model, Mockito.atLeast(1)).moveDown();
		Mockito.verify(model, Mockito.atLeast(1)).dropDown();
		
	}
	
}
















