package game.of.life;

public class Cell {
	
	CellState cellState;

	public Cell(CellState cellState) {
		super();
		this.cellState = cellState;
	}

	

	public enum CellState {
			ALIVE, DEAD
	}



	public CellState getNextState(int i) {
		return i > 1 && i < 4 ? CellState.ALIVE : CellState.DEAD;
	};
	
	
	
}
