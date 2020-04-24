package Snake;

import java.awt.*;
//Represents Board of the game.
public class Board {
    private Cell[][] board;
    private int numRows;
    private int numCols;

    public Board(int numCols,int numRows)
    {
        this.numRows = numRows;
        this.numCols = numCols;
        board = new Cell[this.numRows][this.numCols];
        clearBoard();
    }

    //paint whole board cells black.
    public void clearBoard()
    {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = new Cell(i,j,Color.BLACK);
            }
        }
    }

    public Cell[][] getBoard()
    {
        return this.board;
    }

    public Cell getCell(int row, int col)
    {
        return board[row][col];
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setColor(int row, int col, Color color)
    {
        board[row][col].setColor(color);
    }

    public Color getColor(int row,int col)
    {
        return board[row][col].getColor();
    }
}
