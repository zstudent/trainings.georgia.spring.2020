package Snake;

import java.awt.*;

public class State {
    private Snake snake;
    private Board board;

    private static final int ROWS = 31;
    private static final int COLS = 31;

    public State(int snakeRow,int snakeCol)
    {
        snake = new Snake(snakeRow,snakeCol);
        board = new Board(ROWS,COLS);
        setCellColor(snakeRow,snakeCol,Color.GREEN);
    }

    public void setCellColor(int row, int col, Color color){ board.setColor(row,col,color); }

    public Snake getStateSnake()
    {
        return this.snake;
    }

    public Board getStateBoard()
    {
        return this.board;
    }

}
