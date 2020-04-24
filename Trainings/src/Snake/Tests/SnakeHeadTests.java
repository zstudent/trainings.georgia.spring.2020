package Snake.Tests;

import Snake.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SnakeHeadTests {

    @Test
    public void testLogicStates()
    {
        Logic logic = new Logic(new State(10,10));
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(10,10));
    }

    @Test
    public void testHeadmoveLeft()
    {
        Logic logic = new Logic(new State(10,10));
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
        logic.moveLeft();
        Assertions.assertEquals(9,logic.getSnake().getHeadCol());
        Assertions.assertEquals(Color.BLACK, logic.getBoard().getColor(10,10));
        Assertions.assertEquals(Color.RED, logic.getBoard().getColor(10,9));
    }

    @Test
    public void testHeadmoveRightTooFar()
    {
        Logic logic = new Logic(new State(10,29));
        Assertions.assertEquals(29,logic.getSnake().getHeadCol());
        Assertions.assertEquals(Color.RED, logic.getBoard().getColor(10,29));
        logic.moveRight();
        Assertions.assertEquals(0,logic.getSnake().getHeadCol());
        Assertions.assertEquals(Color.BLACK,logic.getBoard().getColor(10,29));
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(10,0));
    }


    @Test
    public void testHeadmoveRight()
    {
        Logic logic = new Logic(new State(10,10));
        Assertions.assertEquals(Color.RED, logic.getBoard().getColor(10,10));
        logic.moveRight();
        Assertions.assertEquals(Color.BLACK, logic.getBoard().getColor(10,10));
        Assertions.assertEquals(11,logic.getSnake().getHeadCol());
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(10,11));
    }


    @Test
    public void testHeadmoveLeftTooFar()
    {
        Logic logic = new Logic(new State(10,0));
        Assertions.assertEquals(0,logic.getSnake().getHeadCol());
        Assertions.assertEquals(10,logic.getSnake().getHeadRow());
        Assertions.assertEquals(Color.RED, logic.getBoard().getColor(10,0));
        logic.moveLeft();
        Assertions.assertEquals(29,logic.getSnake().getHeadCol());
        Assertions.assertEquals(Color.BLACK,logic.getBoard().getColor(10,0));
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(10,29));
    }

    @Test
    public void testHeadMoveRightAndLeft()
    {
        Logic logic = new Logic(new State(9,10));
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
        Assertions.assertEquals(9,logic.getSnake().getHeadRow());
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(9,10));
        logic.moveLeft();
        logic.moveRight();
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
        Assertions.assertEquals(9,logic.getSnake().getHeadRow());
        Assertions.assertEquals(Color.RED,logic.getBoard().getColor(9,10));
    }

    @Test
    public void testHeadMoveUpAndDown()
    {
        Logic logic = new Logic(new State(10,10));
        Assertions.assertEquals(10,logic.getSnake().getHeadRow());
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
        logic.moveDown();
        Assertions.assertEquals(11,logic.getSnake().getHeadRow());
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
        logic.moveUp();
        Assertions.assertEquals(10,logic.getSnake().getHeadRow());
        Assertions.assertEquals(10,logic.getSnake().getHeadCol());
    }
}