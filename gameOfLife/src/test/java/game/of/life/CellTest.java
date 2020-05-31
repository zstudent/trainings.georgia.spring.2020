package game.of.life;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.of.life.Cell.CellState;

public class CellTest {
	
	@Test
	public void shouldDieWithZeroNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(0);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}

	@Test
	public void shouldDieWithOnlyOneNeighbour() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(1);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}	
	
	@Test
	public void shouldLiveOnWithTwoNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(2);
		
		assertEquals(Cell.CellState.ALIVE, actual);
	}
	
	@Test
	public void shouldLiveOnWithThreeNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(3);
		
		assertEquals(Cell.CellState.ALIVE, actual);
	}
	
	@Test
	public void shouldDieWithFourNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(4);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}	
	
	@Test
	public void shouldDieWithFiveNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(5);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}
	
	@Test
	public void shouldLiveOnWithSixNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(6);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}
	
	@Test
	public void shouldDieWithSevenNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(7);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}
	
	@Test
	public void shouldDieWithEightNeighbours() {
		Cell cell = new Cell(Cell.CellState.ALIVE);
		
		CellState actual = cell.getNextState(8);
		
		assertEquals(Cell.CellState.DEAD, actual);
	}
	
	@Test
	public void shouldBeBornWithThreeNeighbours() {
		Cell cell = new Cell(Cell.CellState.DEAD);
		
		CellState actual = cell.getNextState(3);
		
		assertEquals(Cell.CellState.ALIVE, actual);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
