package Snake.Tests;

import Snake.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class SnakeBodyTests {

    Logic logic;

    @BeforeEach
    public void setUp()
    {
        logic = new Logic(new State(10,10));
    }

    @Test
    public void testInitialSnakeBody()
    {
        assertEquals(0,logic.getSnake().getSnakeBody().size());
    }

    @Test
    public void testSnakeBodyGrow()
    {
        Apple apple = logic.getApple();
        Snake snake = logic.getSnake();
        assertEquals(0,snake.getSnakeBody().size());
        snake.setHeadCol(apple.getCol());
        snake.setHeadRow(apple.getRow()-1);
        logic.moveDown();
        logic.moveLeft();//This is just so body growth updates snake's body.
        LinkedList<Cell> snakeBody = snake.getSnakeBody();
        assertEquals(1,snakeBody.size());
        assertEquals(Color.RED,logic.getBoard().getColor(snakeBody.getLast().getRow(),snakeBody.getLast().getCol()));
    }

    @Test
    public void testConsecutiveSnakeBodyGrow()
    {
        Apple apple = logic.getApple();
        Snake snake = logic.getSnake();
        snake.setHeadCol(apple.getCol());
        snake.setHeadRow(apple.getRow()-1);
        logic.moveDown();
        logic.moveDown();
        apple.setRow(snake.getHeadRow()+1);
        apple.setCol(snake.getHeadCol());
        logic.moveDown();
        logic.moveDown();
        assertEquals(2,snake.getSnakeBody().size());
    }
}
