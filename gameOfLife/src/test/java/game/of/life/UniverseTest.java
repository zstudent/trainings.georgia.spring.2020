package game.of.life;

import static org.junit.Assert.*;
import game.of.life.Cell.CellState;

import org.junit.Test;

public class UniverseTest {

	private static final CellState O = CellState.DEAD;
	private static final CellState X = CellState.ALIVE;

	@Test
	public void shouldStoreItsInitialState() {
		CellState[][] original = new CellState[][] {
			{ X, O, X },
			{ O, O, O },
			{ O, X, X },
		};
		
		Universe uut = new Universe(original);
		
		CellState[][] actual = uut.getState();
		assertArrayEquals(original, actual);
	}

}
