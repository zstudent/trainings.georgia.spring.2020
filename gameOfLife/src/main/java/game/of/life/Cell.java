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



	public void update(int i) {
		if (cellState == CellState.ALIVE) {
			cellState = i > 1 && i < 4 ? CellState.ALIVE : CellState.DEAD;
		} else {
			cellState = i == 3 ? CellState.ALIVE : CellState.DEAD;
		}
			
	}


	
	public CellState getState() {
		return cellState;
	};
	
	
	
}
