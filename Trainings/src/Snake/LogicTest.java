package Snake;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    public void testLogicStates()
    {
        Logic logic = new Logic(new State(10,10));
        assertEquals(Color.RED,logic.getBoard().getColor(10,10));
    }

    @Test
    public void moveLeft()
    {
        Logic logic = new Logic(new State(10,10));
        assertEquals(10,logic.getSnake().getHeadCol());
        logic.moveLeft();
        assertEquals(11,logic.getSnake().getHeadCol());
        assertEquals(Color.BLACK, logic.getBoard().getColor(10,10));
        assertEquals(Color.RED, logic.getBoard().getColor(10,11));
    }

    @Test
    public void moveLeftTooFar()
    {
        Logic logic = new Logic(new State(10,29));
        assertEquals(29,logic.getSnake().getHeadCol());
        assertEquals(Color.RED, logic.getBoard().getColor(10,29));
        logic.moveLeft();
        assertEquals(0,logic.getSnake().getHeadCol());
        assertEquals(Color.BLACK,logic.getBoard().getColor(10,29));
        assertEquals(Color.RED,logic.getBoard().getColor(10,0));
    }


    @Test
    public void moveRight()
    {
        Logic logic = new Logic(new State(10,10));
        assertEquals(Color.RED, logic.getBoard().getColor(10,10));
        logic.moveRight();
        assertEquals(Color.BLACK, logic.getBoard().getColor(10,10));
        assertEquals(9,logic.getSnake().getHeadCol());
        assertEquals(Color.RED,logic.getBoard().getColor(10,9));
    }


    @Test
    public void moveRightTooFar()
    {
        Logic logic = new Logic(new State(10,0));
        assertEquals(0,logic.getSnake().getHeadCol());
        assertEquals(10,logic.getSnake().getHeadRow());
        assertEquals(Color.RED, logic.getBoard().getColor(10,0));
        logic.moveRight();
        assertEquals(29,logic.getSnake().getHeadCol());
        assertEquals(Color.BLACK,logic.getBoard().getColor(10,0));
        assertEquals(Color.RED,logic.getBoard().getColor(10,29));
    }

    @Test
    public void moveRightAndLeft()
    {
        Logic logic = new Logic(new State(9,10));
        assertEquals(10,logic.getSnake().getHeadCol());
        assertEquals(9,logic.getSnake().getHeadRow());
        assertEquals(Color.RED,logic.getBoard().getColor(9,10));
        assertNotEquals(Color.RED,logic.getBoard().getColor(9,11));
        assertNotEquals(Color.RED,logic.getBoard().getColor(9,8));
        moveLeft();
        moveRight();
        assertEquals(10,logic.getSnake().getHeadCol());
        assertEquals(9,logic.getSnake().getHeadRow());
        assertEquals(Color.RED,logic.getBoard().getColor(9,10));
        assertNotEquals(Color.RED,logic.getBoard().getColor(9,11));
        assertNotEquals(Color.RED,logic.getBoard().getColor(9,8));
    }
}