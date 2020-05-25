package GameOfLife.MVC.Helpers;

import java.util.Random;

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

    public boolean isStateEmpty(){
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                if(isCellAlive(row,col))return false;
            }
        }
        return true;
    }

    public void fillStateWithRandomCells(){
        int initNumberOfAliveCells = 200;
        Random random = new Random();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                if(initNumberOfAliveCells>=0) {
                    gameBoard[row][col] = random.nextBoolean();
                    initNumberOfAliveCells--;
                }
                else break;
            }
        }
//        gameBoard[15][15] = true;
//        gameBoard[15][16]=true;
//        gameBoard[15][17] = true;
//        gameBoard[14][15]=true;
    }
}
