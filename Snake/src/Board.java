public class Board {
    /*
    board = {
  height
    ^
    *{       }
    *{       }
    *{       }
    ********** > width
    }
     */

    private int[][] board;
    private int rows;
    private int columns;

    public Board(int rows, int columns){
        board = new int[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    public int getBoard(int row, int column){
        if(column>=0&&column<this.columns&&row>=0&&row<this.rows){
            return board[row][column];
        }
        return 1;
    }

    public void setBoard(int row, int column, int val){
        board[row][column] = val;
    }

    public int getWidth(){
        return rows;
    }

    public int getHeight(){
        return columns;
    }



}
