package tetris;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class ModelTest {

	private Model model;

	@BeforeEach
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
		// TODO:  check onCHanged is called!

		model.addListener(new ModelListener() {
			@Override
			public void fireGameOver(String msg) { }
			@Override
			public void onChange(State state) {
				assertNotNull(state);
			}
			@Override
			public void fireScoreChange(int numClearedRows, boolean restart) { }
		});
		assertFalse(model.listeners.isEmpty());
		model.moveLeft();
	}

}
