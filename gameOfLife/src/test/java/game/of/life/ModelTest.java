package game.of.life;

import static org.junit.Assert.*;
import game.of.life.Cell.CellState;

import org.junit.Test;

public class ModelTest {

	private static final CellState O = CellState.DEAD;
	private static final CellState X = CellState.ALIVE;

	@Test
	public void shouldStoreItsInitialState() {
		CellState[][] original = new CellState[][] {
			{ X, O, X },
			{ O, O, O },
			{ O, X, X },
		};
		
		Model uut = new Model(original);
		
		CellState[][] actual = uut.getState();
		assertArrayEquals(original, actual);
	}
	
	@Test
	public void shouldUpdateCell() {
		Model uut = new Model(new CellState[][] { { X } });
		
		uut.update();
		
		CellState[][] actual = uut.getState();
		assertEquals(CellState.DEAD, actual[0][0]);
	}
	
	@Test
	public void shouldUpdateAllCells() {
		Model uut = new Model(new CellState[][] {
			{ O, X, X },
			{ X, O, X },
			{ O, O, X }
		});
		
		CellState[][] expected = new CellState[][] {
			{ O, X, X },
			{ O, O, X },
			{ O, X, O }
		};
		
		uut.update();
		CellState[][] actual = uut.getState();
		
		assertArrayEquals(expected, actual);		
	}
	
	@Test
	public void shouldConsiderAllNeighbours() {
		Model uut = new Model(new CellState[][] {
			{ X, X, X },
			{ X, X, X },
			{ X, X, X }
		});
		
		CellState[][] expected = new CellState[][] {
			{ X, O, X },
			{ O, O, O },
			{ X, O, X }
		};
		
		CellState[][] actual = getNextState(uut);
		
		assertArrayEquals(expected, actual);		
	}
	
	private CellState[][] getNextState(Model uut) {
		uut.update();
		return uut.getState();
	}
	
	
	
	
	
	
	
	
	

}
