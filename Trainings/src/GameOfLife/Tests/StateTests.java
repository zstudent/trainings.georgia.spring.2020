package GameOfLife.Tests;

import GameOfLife.MVC.Helpers.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTests {

    @Test
    public void emptyState(){
        State state = new State(30,30);
        assertEquals(true,state.isStateEmpty());
    }

    @Test
    public void testNotInBounds(){
        State state = new State(30,30);
        assertEquals(false,state.isInBounds(-1,-1));
        assertEquals(false,state.isInBounds(30,30));
    }

    @Test
    public void testInBounds(){
        State state = new State(30,30);
        assertEquals(true,state.isInBounds(29,29));
        assertEquals(true,state.isInBounds(0,0));
    }

    @Test
    public void testPointToNewBoard(){
        State state = new State(30,30);
        state.setNewStateOfCell(0,0,true);
        state.setNewStateOfCell(29,29,true);
        assertEquals(false,state.isStateEmpty());
        state.pointToANewGameBoard(new State(30,30));
        assertEquals(true,state.isStateEmpty());

    }
}
