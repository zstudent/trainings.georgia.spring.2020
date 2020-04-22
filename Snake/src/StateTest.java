import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void testConstructor(){
        State state = new State(20,30);
        assertEquals(0, state.score);
        assertEquals(2, state.body.size());
    }

    private boolean containsFruit(State state){
        for (int i = 0; i <state.board.getWidth();i++){
            for(int j = 0; j < state.board.getHeight();j ++){
                if(state.board.getBoard(i,j)==2){
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testStartGame(){
        State state = new State(20,30);
        assertFalse(containsFruit(state));
        state.startGame();
        assertTrue(containsFruit(state));
    }

    @Test
    public void testDirection(){
        State state = new State(20,30);
        state.changeDirection(Direction.LEFT);
        assertEquals(Direction.RIGHT, state.body.currentDirection);
        state.changeDirection(Direction.UP);
        assertEquals(Direction.UP, state.body.currentDirection);
    }

}