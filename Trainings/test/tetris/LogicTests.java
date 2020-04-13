package tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogicTests {

	private Logic logic;

	@Before
	public void setUp() {
		logic = new Logic(new State());
	}
	
	@Test
	public void testLeft() {
		int col = logic.state.col;
		boolean success = logic.moveLeft();
		assertEquals(col - 1, logic.state.col);
		assertTrue(success);
	}
	
	@Test
	public void testMovedLeftTooFar() {
		logic.state.col = 0;
		logic.state.figure = new Figure(Figure.FOUR_HORIZONTAL);
		boolean success = logic.moveLeft();
		assertEquals(0, logic.state.col);
		assertFalse(success);
	}
	
	@Test
	public void testRight() {
		int col = logic.state.col;
		boolean success = logic.moveRight();
		assertEquals(col + 1, logic.state.col);
		assertTrue(success);
	}
	
	@Test
	public void testMovedRightTooFar() {
		int col = fieldWidth() - figureWidth();
		logic.state.col = col;
		logic.state.figure = new Figure(Figure.FOUR_HORIZONTAL);
		boolean success = logic.moveRight();
		assertEquals(col, logic.state.col);
		assertFalse(success);
	}

	private int figureWidth() {
		return logic.state.figure.data[0].length;
	}

	private int fieldWidth() {
		return logic.state.field.data[0].length;
	}
	
	@Test
	public void testMovedDown() throws Exception {
		int row = logic.state.row;
		boolean success = logic.moveDown();
		assertEquals(row + 1, logic.state.row);
		assertTrue(success);
	}

	@Test
	public void testMovedDownTooFar() throws Exception {
		logic.state.row = fieldHeight() - 1;
		int row = logic.state.row;
		boolean success = logic.moveDown();
		assertEquals(row, logic.state.row);
		assertFalse(success);
	}

	private int fieldHeight() {
		return logic.state.field.data.length;
	}
}

