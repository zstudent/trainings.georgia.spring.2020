package tetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstantiationsTest {

	@Test
	public void testFieldCreation() {
		Field field = new Field(10, 20);
		assertNotNull(field.data);
		assertEquals(field.data.length, 20);
		assertEquals(field.data[0].length, 10);
		// we may add here check that all data cells are zeroed
	}
	
	@Test
	public void testFigureCreation() throws Exception {
		Figure figure = new Figure();
		assertNotNull(figure.data);
		assertEquals(figure.data.length, 4);
		assertEquals(figure.data[0].length, 4);
	}
	
	@Test
	public void testConcreteFigureCreation() throws Exception {
		Figure figure = new Figure(Figure.I);
		assertNotNull(figure.data);
		assertTrue(figure.data == Figure.I);
		assertEquals(figure.data[0].length, 4);
	}
	
	@Test
	public void testStateCreation() throws Exception {
		State state = new State();
		assertNotNull(state.field);
		assertNotNull(state.figure);
		assertEquals(state.row, 0);
		int pos = state.field.data[0].length/2 - state.figure.data[0].length/2;
		assertEquals(state.col, pos);
	}
	
	@Test
	public void testLogicCreation() throws Exception {
		State state = new State();
		Logic logic = new Logic(state);
		assertNotNull(logic.state);
		assertEquals(logic.state, state);
	}

}
