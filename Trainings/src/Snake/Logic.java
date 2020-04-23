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
    private int prevDirection;

    public boolean moveRight()
    {
        moveSnake(0,1);
        prevDirection = currentDirection;
        currentDirection = DIRECTION_RIGHT;
        return true;
    }

    public boolean moveLeft()
    {
        moveSnake(0,-1);
        prevDirection = currentDirection;
        currentDirection = DIRECTION_LEFT;
        return true;
    }


    public boolean moveDown()
    {
        moveSnake(1,0);
        prevDirection = currentDirection;
        currentDirection = DIRECTION_DOWN;
        return true;
    }


    public boolean moveUp()
    {
        moveSnake(-1,0);
        prevDirection = currentDirection;
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
        Snake snake = state.getStateSnake();
        Cell snakeHead = snake.getSnakeHead();
        LinkedList<Cell> snakeBody = snake.getSnakeBody();
        Cell snakeTail = snake.getTail();
        state.removeSnakeFromBoard();
        snakeEatsApple();
        snakeHead.setCol(snakeHead.getCol() + deltaCol);
        snakeHead.setRow(snakeHead.getRow() + deltaRow);
        if(snakeHead.getCol() < 0) snakeHead.setCol(state.getStateBoard().getNumCols()-1);
        if(snakeHead.getRow() < 0) snakeHead.setRow(state.getStateBoard().getNumRows()-1);
        if(snakeHead.getRow() > state.getStateBoard().getNumRows()-1) snakeHead.setRow(0);
        if(snakeHead.getCol() > state.getStateBoard().getNumCols()-1) snakeHead.setCol(0);
        for(Cell tmp : snakeBody)
        {
            tmp.setCol(tmp.getCol()+deltaCol);
            tmp.setRow(tmp.getRow()+deltaRow);
            if(tmp.getCol() < 0) tmp.setCol(state.getStateBoard().getNumCols()-1);
            if(tmp.getRow() < 0) tmp.setRow(state.getStateBoard().getNumRows()-1);
            if(tmp.getRow() > state.getStateBoard().getNumRows()-1) tmp.setRow(0);
            if(tmp.getCol() > state.getStateBoard().getNumCols()-1) tmp.setCol(0);

        }
        state.colorSnakeOnBoard();
    }

    private void snakeEatsApple()
    {
        if(snakeMeetsApple())
        {
            Snake snake = state.getStateSnake();
            state.generateApple();
            snake.growSnake(currentDirection);
        }

        //Snake ++
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

    private void repaintSnakeOnBoard()
    {

    }

}
