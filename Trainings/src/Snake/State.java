package Snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class State {
    private Snake snake;
    private Board board;
    private Apple apple;

    private static final int ROWS = 30;
    private static final int COLS = 30;
    private static Random random;

    public State(int snakeRow,int snakeCol)
    {
        random = new Random();
        snake = new Snake(snakeRow,snakeCol);
        board = new Board(ROWS,COLS);
        apple = new Apple(0,0);
        setCellColor(snakeRow,snakeCol,Color.RED);
        generateApple();
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

    public Apple getStateApple(){ return this.apple; }

    public void generateApple()
    {
        int row = random.nextInt(board.getNumRows());
        int col = random.nextInt(board.getNumCols());
        apple.setCol(col);
        apple.setRow(row);
        board.setColor(row,col,apple.getColor());
    }

    public void colorSnakeOnBoard()
    {
        board.setColor(snake.getHeadRow(),snake.getHeadCol(),Color.RED);
        LinkedList<Cell> body = snake.getSnakeBody();
        if(body.size()>0)
        {
            for(Cell c : body)
            {
                board.setColor(c.getRow(),c.getCol(),c.getColor());
            }
        }
    }

    public void removeSnakeFromBoard()
    {
        board.setColor(snake.getHeadRow(),snake.getHeadCol(),Color.BLACK);
        LinkedList<Cell> body = snake.getSnakeBody();
        if(body.size()>0)
        {
            for(Cell c : body)
            {
                board.setColor(c.getRow(),c.getCol(),Color.BLACK);
            }
        }
    }
}
