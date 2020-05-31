package game.of.life;

import org.junit.Test;

import game.of.life.Cell.CellState;

public class Universe {
	
	private Cell[][] state;
	
	public Universe(CellState[][] cellStates) {
		int LENGTH = cellStates.length;
		state = new Cell[LENGTH][];
		for (int row = 0; row < LENGTH; row++) {
			state[row] = new Cell[LENGTH];
			for (int col = 0; col < LENGTH; col++) {
				state[row][col] = new Cell(cellStates[row][col]);
			}
		}
	}

	public CellState[][] getState() {
		CellState[][] cellStates = new CellState[state.length][];
		for (int row = 0; row < cellStates.length; row++) {
			int LENGTH = state[row].length;
			cellStates[row] = new CellState[LENGTH];
			for (int col = 0; col < LENGTH; col++) {
				cellStates[row][col] = state[row][col].getState();
			}
		}
		return cellStates;
	}

	public void update() {
		CellState[][] cellStates = getState();
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state.length; col++) {
				int numberOfAliveNeighbours = getNumberOfAliveNeighbours(cellStates, row, col);
				state[row][col].update(numberOfAliveNeighbours);
			}
		}
	}

	private int getNumberOfAliveNeighbours(CellState[][] state, int row, int col) {
		int numberOfNeighbours = 0;
		if (row > 0) {
			int rowAbove = row - 1;
			if (col > 0) {
				if (state[rowAbove][col - 1] == CellState.ALIVE) numberOfNeighbours++;
			}
			if (state[rowAbove][col] == CellState.ALIVE) numberOfNeighbours++;
			if (col < state[row].length - 1) {
				if (state[rowAbove][col + 1] == CellState.ALIVE) numberOfNeighbours++;
			}
		}
		
		if (row < state.length - 1) {
			int rowBelow = row + 1;
			if (col > 0) {
				if (state[rowBelow][col - 1] == CellState.ALIVE) numberOfNeighbours++;
			}
			if (state[rowBelow][col] == CellState.ALIVE) numberOfNeighbours++;
			if (col < state[row].length - 1) {
				if (state[rowBelow][col + 1] == CellState.ALIVE) numberOfNeighbours++;
			}
		}
		
		if (col > 0) {
			if (state[row][col - 1] == CellState.ALIVE) numberOfNeighbours++;
		}
		
		if (col < state[row].length - 1) {
			if (state[row][col + 1] == CellState.ALIVE) numberOfNeighbours++;
		}
		
		return numberOfNeighbours;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
