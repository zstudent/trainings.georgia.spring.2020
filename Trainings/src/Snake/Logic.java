package Snake;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    //Constants for determining directions of snake.
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_UP = 2;
    private static final int DIRECTION_DOWN = -2;
    //Initial timer is 300(ms).
    private int timer = 300;
    private State state;


    public Logic(State state)
    {
        this.state = state;
    }
    //Describes current direction of snake.
    private int currentDirection;

    //If moveXXXX method return false,
    //it means that game is over.
    public boolean moveRight()
    {
        if (snakeMeetsItself()) return false;
        moveSnake(0,1);
        currentDirection = DIRECTION_RIGHT;
        return true;
    }

    public boolean moveLeft()
    {
        if (snakeMeetsItself()) return false;
        moveSnake(0,-1);
        currentDirection = DIRECTION_LEFT;
        return true;
    }

    public boolean moveDown()
    {
        if (snakeMeetsItself()) return false;
        moveSnake(1,0);
        currentDirection = DIRECTION_DOWN;
        return true;
    }

    public boolean moveUp()
    {
        if (snakeMeetsItself()) return false;
        moveSnake(-1,0);
        currentDirection = DIRECTION_UP;
        return true;
    }

    //Returns board obj.
    public Board getBoard()
    {
        return state.getStateBoard();
    }

    //Returns snake obj.
    public Snake getSnake()
    {
        return state.getStateSnake();
    }
    //Returns apple obj.
    public Apple getApple() { return state.getStateApple(); }
    //Returns timer value.
    public int getTimer(){ return timer; }

    private void moveSnake(int deltaRow,int deltaCol)
    {
        snakeEatsApple();
        int newRow = state.getStateSnake().getSnakeHead().getRow()+deltaRow;
        int newCol = state.getStateSnake().getSnakeHead().getCol()+deltaCol;
        if(newCol < 0) newCol = state.getStateBoard().getNumCols()-1;
        if(newRow < 0) newRow = state.getStateBoard().getNumRows()-1;
        if(newRow > state.getStateBoard().getNumRows()-1) newRow = 0;
        if(newCol > state.getStateBoard().getNumCols()-1) newCol = 0;
        LinkedList<Cell> body = state.getStateSnake().getSnakeBody();
        //If body is empty modify only head of snake.
        if(body.isEmpty()){
            state.getStateBoard().setColor(state.getStateSnake().getSnakeHead().getRow(), state.getStateSnake().getSnakeHead().getCol(),Color.BLACK);
            state.getStateSnake().setHeadCol(newCol);
            state.getStateSnake().setHeadRow(newRow);
            state.getStateBoard().setColor(state.getStateSnake().getHeadRow(),state.getStateSnake().getHeadCol(),Color.RED);
        }else{
            //If snake has body, removeLast cell, add new one in front.
            Cell newHead = new Cell(newRow,newCol,Color.RED);
            state.getStateBoard().setColor(body.getLast().getRow(), body.getLast().getCol(),Color.BLACK);
            state.getStateSnake().getSnakeBody().removeLast();
            state.getStateSnake().getSnakeBody().addFirst(state.getStateSnake().getSnakeHead());
            state.getStateSnake().setHead(newHead);
            state.getStateBoard().setColor(newHead.getRow(),newHead.getCol(),Color.RED);
        }
    }

    //Snake/apple relationship.
    private void snakeEatsApple()
    {
        if(snakeMeetsApple())
        {
            state.generateApple();
            state.getStateSnake().growSnake(currentDirection);
            timer*=0.9;
        }
    }

    //Returns true if snake's head met apple.
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

    //Returns true if snake's head met his body part.
    private boolean snakeMeetsItself()
    {
        Cell head = state.getStateSnake().getSnakeHead();
        LinkedList<Cell> snakeBody = state.getStateSnake().getSnakeBody();
        for(Cell element : snakeBody)
        {
            if(element.getCol() == head.getCol() && element.getRow() == head.getRow()) return true;
        }
        return false;
    }
}
