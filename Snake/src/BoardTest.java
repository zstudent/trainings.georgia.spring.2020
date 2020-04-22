import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testConstructor() {
        Board board = new Board(30,20);
        assertEquals(20,board.getHeight());
        assertEquals(30,board.getWidth());
    }

    @Test
    public void testGetBoard(){
        Board board = new Board(30,20);
        assertEquals(0,board.getBoard(5,8));
        assertEquals(1,board.getBoard(31,8));
        board.setBoard(4,6,1);
        assertEquals(1,board.getBoard(4,6));
    }
}