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
		if (cellState == CellState.ALIVE) {
			return i > 1 && i < 4 ? CellState.ALIVE : CellState.DEAD;
		} else {
			return i == 3 ? CellState.ALIVE : CellState.DEAD;
		}
			
	}


	
	public CellState getState() {
		return cellState;
	};
	
	
	
}
