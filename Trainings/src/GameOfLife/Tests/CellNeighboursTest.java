package GameOfLife.Tests;

import GameOfLife.MVC.Helpers.Logic;
import GameOfLife.MVC.Helpers.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellNeighboursTest {

    @Test
    public void testNoNeighbours(){
        State state = new State(10,10);
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        state.setNewStateOfCell(3,3,true);
        assertEquals(0,logic.getNumberOfLivingNeighbours(3,3));
    }


    @Test
    public void testThreeNeighbours(){
        State state = new State(10,10);
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        state.setNewStateOfCell(3,3,true);
        state.setNewStateOfCell(3,4,true);
        state.setNewStateOfCell(2,3,true);
        state.setNewStateOfCell(2,2,true);
        assertEquals(3,logic.getNumberOfLivingNeighbours(3,3));
    }

    @Test
    public void testNeighboursAtEdgeOfGameBoard(){
        State state = new State(10,10);
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        state.setNewStateOfCell(0,0,true);
        state.setNewStateOfCell(1,0,true);
        state.setNewStateOfCell(0,1,true);
        state.setNewStateOfCell(1,1,true);
        assertEquals(3,logic.getNumberOfLivingNeighbours(0,0));
    }

    @Test
    public void testNeighboursAtEdgeOfGameBoard2(){
        State state = new State(10,10);
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        state.setNewStateOfCell(9,9,true);
        state.setNewStateOfCell(8,9,true);
        state.setNewStateOfCell(9,8,true);
        assertEquals(2,logic.getNumberOfLivingNeighbours(9,9));
    }

    @Test
    public void testHasAllNeighbours(){
        State state = new State(10,10);
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        state.setNewStateOfCell(5,5,true);
        state.setNewStateOfCell(5,6,true);
        state.setNewStateOfCell(6,6,true);
        state.setNewStateOfCell(6,5,true);
        state.setNewStateOfCell(4,5,true);
        state.setNewStateOfCell(4,4,true);
        state.setNewStateOfCell(5,4,true);
        state.setNewStateOfCell(6,4,true);
        state.setNewStateOfCell(4,6,true);
        assertEquals(8,logic.getNumberOfLivingNeighbours(5,5));
    }
}
