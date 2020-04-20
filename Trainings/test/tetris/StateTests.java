package tetris;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StateTests {

	@Test
	public void testRemoveFullLine() {
		State state = new State();
		int[] lastRow = state.field.data[State.ROWS-1];
		int[] previousRow = state.field.data[State.ROWS-2];
		
		Arrays.fill(lastRow, 1);
		Arrays.fill(previousRow, 2);
		previousRow[state.col] = 0;
		
		state.field.removeFilledRows();
		
		assertEquals(0, lastRow[state.col]);
		assertEquals(2, lastRow[0]);
		assertEquals(0, previousRow[0]);
		assertEquals(0, previousRow[state.col]);
	}

	@Test
	public void testStateHeight()
	{
		State state = new State();
		state.figure = new Figure(Figure.L);
		assertEquals(3,state.figure.figureHeight);
		state.rotateFigureLeft();
		assertEquals(2,state.figure.figureHeight);
		state.undo();
		assertEquals(3,state.figure.figureHeight);
	}
}
