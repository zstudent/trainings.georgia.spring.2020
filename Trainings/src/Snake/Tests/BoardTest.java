package Snake.Tests;

import Snake.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void testCreateBoard()
    {
        Board board = new Board(10,10);
        for(int i=0; i<board.getNumRows(); i++)
        {
            for (int j = 0; j < board.getNumCols(); j++) {
                Assertions.assertEquals(Color.BLACK,board.getColor(i,j));
            }
        }
    }

    @Test
    public void testColorBoard()
    {
        Board board = new Board(10,10);
        board.setColor(0,0,Color.RED);
        Assertions.assertEquals(Color.RED,board.getColor(0,0));
    }

    @Test
    public void testClearingBoard()
    {
        Board board = new Board(10,10);
        board.setColor(9,9,Color.RED);
        Assertions.assertEquals(Color.RED,board.getColor(9,9));
        board.clearBoard();
        Assertions.assertEquals(Color.BLACK,board.getCell(9,9).getColor());
    }
}