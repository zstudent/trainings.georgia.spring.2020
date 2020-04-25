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
        logic.SANITY_CHECK = true;
    }

    @Test
    public void testInitialSnakeBody()
    {
        assertEquals(0,logic.getSnake().getSnakeBody().size());
    }

    @Test
    public void testSnakeBodyGrow()
    {
        Food apple = logic.getApple();
        Snake snake = logic.getSnake();
        assertEquals(0,snake.getSnakeBody().size());
        snake.setHeadCol(apple.getCol());
        snake.setHeadRow(apple.getRow()-1);
        logic.moveDown();
        logic.moveDown();
        LinkedList<Cell> snakeBody = snake.getSnakeBody();
        assertEquals(1,snakeBody.size());
        assertEquals(Color.RED,logic.getBoard().getColor(snakeBody.getLast().getRow(),snakeBody.getLast().getCol()));
    }

    @Test
    public void testConsecutiveSnakeBodyGrow()
    {
        Food apple = logic.getApple();
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

    @Test
    public void testSnakeEatsBorderApple()
    {
        Food apple = logic.getApple();
        Snake snake = logic.getSnake();
        apple.setCol(0);
        apple.setRow(0);
        snake.setHeadCol(0);
        snake.setHeadRow(1);
        logic.moveUp();
        logic.moveUp();
        assertEquals(1,snake.getSnakeBody().size());
        assertEquals(logic.getBoard().getNumRows()-1,snake.getHeadRow());
    }

    @Test
    public void testSnakeEatsBorderApple_2()
    {
        Food apple = logic.getApple();
        Snake snake = logic.getSnake();
        apple.setCol(0);
        apple.setRow(0);
        snake.setHeadCol(1);
        snake.setHeadRow(0);
        logic.moveLeft();
        logic.moveLeft();
        assertEquals(1,snake.getSnakeBody().size());
        assertEquals(logic.getBoard().getNumCols()-1,snake.getHeadCol());
    }
}
