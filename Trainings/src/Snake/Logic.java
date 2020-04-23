package Snake;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_UP = 2;
    private static final int DIRECTION_DOWN = -2;

    private State state;

    public Logic(State state)
    {
        this.state = state;
    }

    private int currentDirection;

    public boolean moveRight()
    {
        moveSnake(0,1);
        currentDirection = DIRECTION_RIGHT;
        return true;
    }

    public boolean moveLeft()
    {
        moveSnake(0,-1);
        currentDirection = DIRECTION_LEFT;
        return true;
    }


    public boolean moveDown()
    {
        moveSnake(1,0);
        currentDirection = DIRECTION_DOWN;
        return true;
    }


    public boolean moveUp()
    {
        moveSnake(-1,0);
        currentDirection = DIRECTION_UP;
        return true;
    }

    public Board getBoard()
    {
        return state.getStateBoard();
    }

    public Snake getSnake()
    {
        return state.getStateSnake();
    }

    private void moveSnake(int deltaRow,int deltaCol)
    {
        LinkedList<Cell> body = state.getStateSnake().getSnakeBody();
        snakeEatsApple();
        Cell newHead = new Cell(0,0,Color.RED);
        int newRow = state.getStateSnake().getSnakeHead().getRow()+deltaRow;
        int newCol = state.getStateSnake().getSnakeHead().getCol()+deltaCol;
        if(newCol < 0) newCol = (state.getStateBoard().getNumCols()-1);
        if(newRow < 0) newRow = (state.getStateBoard().getNumRows()-1);
        if(newRow > state.getStateBoard().getNumRows()-1) newRow = (0);
        if(newCol > state.getStateBoard().getNumCols()-1) newCol = (0);
        if(body.isEmpty()){
            state.getStateBoard().setColor(state.getStateSnake().getSnakeHead().getRow(), state.getStateSnake().getSnakeHead().getCol(),Color.BLACK);
            state.getStateSnake().setHeadCol(newCol);
            state.getStateSnake().setHeadRow(newRow);
            state.getStateBoard().setColor(state.getStateSnake().getHeadRow(),state.getStateSnake().getHeadCol(),Color.RED);
        }else{
            state.getStateBoard().setColor(body.getLast().getRow(), body.getLast().getCol(),Color.BLACK);
            state.getStateSnake().getSnakeBody().removeLast();
            state.getStateSnake().getSnakeBody().addFirst(state.getStateSnake().getSnakeHead());
            newHead.setCol(newCol);
            newHead.setRow(newRow);
            state.getStateSnake().setHead(newHead);
            state.getStateBoard().setColor(newHead.getRow(),newHead.getCol(),Color.RED);
        }

    }

    private void snakeEatsApple()
    {
        if(snakeMeetsApple())
        {
            Snake snake = state.getStateSnake();
            state.generateApple();
            snake.growSnake(currentDirection);
        }
    }

    private boolean snakeMeetsApple()
    {
        Snake snake = state.getStateSnake();
        Apple apple = state.getStateApple();
        if(snake.getHeadCol()==apple.getCol() && snake.getHeadRow() == apple.getRow())
        {
            return true;
        }
        return false;
    }
}
