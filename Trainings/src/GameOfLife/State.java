package GameOfLife;

public class State {
    private boolean [][] gameBoard;

    public State(int numRows,int numCols){
        gameBoard = new boolean[numRows][numCols];
    }

    public int getGameBoardRows(){
        return gameBoard.length;
    }

    public int getGameBoardColumns(){
        return gameBoard[0].length;
    }

    public boolean isCellAlive(int row, int col){
        return gameBoard[row][col];
    }

    public void setNewStateOfCell(int row, int col, boolean value){
        gameBoard[row][col] = value;
    }

    public boolean[][] getGameBoard(){
        return this.gameBoard;
    }

    public void pointToANewGameBoard(State newState){
        this.gameBoard = newState.getGameBoard();
    }

    public boolean isInBounds(int row,int col){
        return row < getGameBoardRows() && row >=0
                && col < getGameBoardColumns() && col >=0 ;
    }
}
