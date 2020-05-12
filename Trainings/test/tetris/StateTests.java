package tetris;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StateTests {

	@Test
	public void testRemoveFullLine() {
		State state = new State();
		int[] lastRow = state.field.data[State.ROWS-1];
		int[] previousRow = state.field.data[State.ROWS-2];
		
		Arrays.fill(lastRow, 1);
		Arrays.fill(previousRow, 2);
		previousRow[state.col] = 0;
		
		state.field.removeFilledRows(state);
		
		assertEquals(0, lastRow[state.col]);
		assertEquals(2, lastRow[0]);
		assertEquals(0, previousRow[0]);
		assertEquals(0, previousRow[state.col]);
	}

}
