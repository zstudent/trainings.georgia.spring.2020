package Snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

//Represents one snapShotted state of game.
public class State {
    //Has snake and board in snapshot.
    private Snake snake;
    private Board board;
    private Apple apple;

    //Rows and Cols of board.
    private static final int ROWS = 30;
    private static final int COLS = 30;
    //Random, used for placing apple.
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
        //Don't let generated row/col be snake's part.
        while(!validAppleLocation(row,col))
        {
            row = random.nextInt(board.getNumRows());
            col = random.nextInt(board.getNumCols());
        }
        apple.setCol(col);
        apple.setRow(row);
        board.setColor(row,col,apple.getColor());
    }

    //This function return true if apple wasn't located on  snake's (any) coordinates.
    private boolean validAppleLocation(int appleRow,int appleCol)
    {
        if(snake.getSnakeHead().getCol()==appleCol && snake.getSnakeHead().getRow()==appleRow) return false;
        for(Cell element : snake.getSnakeBody())
        {
            if(element.getRow()==appleRow && element.getCol() == appleCol) return false;
        }
        return true;
    }
}
