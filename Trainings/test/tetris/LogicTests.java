package tetris;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class LogicTests {

	private Logic logic;

	@BeforeEach
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
		logic.state.figure = new Figure(Figure.I);
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
		logic.state.figure = new Figure(Figure.I);
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
		int success = logic.moveDown();
		assertEquals(row + 1, logic.state.row);
		assertTrue(success==0);
	}

	@Test
	public void testMovedDownTooFar() throws Exception {
		logic.state.figure = new Figure(Figure.I);
		logic.state.row = fieldHeight() - 1;
		logic.state.col = 0;
		int row = logic.state.row;
		Figure old = logic.state.figure; 
		int success = logic.moveDown();
		assertFalse(old == logic.state.figure);
		assertEquals(0, logic.state.row);
		assertTrue(success==0);
		// TODO HOMEWORK:  check that old figure is copied to the field
		//Checking
		System.out.println(logic.state.field.toString());
		System.out.println(row);
		System.out.println(logic.state.row);
		for(int i=0; i<4; i++)assertTrue(logic.state.field.data[row][i]>0);

	}

	private int fieldHeight() {
		return logic.state.field.data.length;
	}
}

