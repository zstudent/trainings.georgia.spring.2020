package game.of.life;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.of.life.Cell.CellState;

public class Model {

	private Cell[][] state;
	
	List<ModelListener> listeners = new ArrayList<>();

	public Model(CellState[][] cellStates) {
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
		fireOnChange();
	}

	private int getNumberOfAliveNeighbours(CellState[][] state, int row, int col) {
		int numberOfNeighbours = 0;
		numberOfNeighbours += getNumbersOfAliveNeighboursInRow(state, row - 1, col);
		numberOfNeighbours += getNumbersOfAliveNeighboursInRow(state, row + 1, col);
		numberOfNeighbours += getCountIfCellIsAlive(state, row, col - 1);
		numberOfNeighbours += getCountIfCellIsAlive(state, row, col + 1);
		return numberOfNeighbours;
	}

	private int getNumbersOfAliveNeighboursInRow(CellState[][] state, int row, int col) {
		int numberOfNeighbours = 0;
		if (row >= 0 && row < state.length) {
			numberOfNeighbours += getCountIfCellIsAlive(state, row, col - 1);
			numberOfNeighbours += getCountIfCellIsAlive(state, row, col);
			numberOfNeighbours += getCountIfCellIsAlive(state, row, col + 1);
		}
		return numberOfNeighbours;
	}

	private int getCountIfCellIsAlive(CellState[][] state, int row, int col) {
		if (col >= 0 && col < state[row].length) {
			if (state[row][col] == CellState.ALIVE)
				return 1;
		}
		return 0;
	}

	public void addListener(ModelListener modelListener) {
		listeners.add(modelListener);
	}

	public void refreshView() {
		fireOnChange();
	}

	private void fireOnChange() {
		for (ModelListener listener : listeners) {
			listener.onChange(this);
		}
	}

}
