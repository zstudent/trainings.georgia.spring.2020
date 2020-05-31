package game.of.life;

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

}
