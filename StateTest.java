import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StateTest {
	
	@Test
	public void testConstructor() {
		State state = new State();
		assertNotNull(state.getBoard());
		assertNotNull(state.getBody());
		assertEquals(state.getBody().getHead().row, (JSnake.HEIGHT/2)-1);
		assertEquals(state.getBody().getHead().col, (JSnake.WIDTH/2)-1);
	}
	
	
	@Test
	public void testMove1() {
		State state = new State();
		int headOldRow = state.getBody().getHead().row;
		int headOldCol = state.getBody().getHead().col;
		int tailOldRow = state.getBody().getTail().row;
		int tailOldCol = state.getBody().getTail().col;
		
		System.out.println(state.getBoard().toString());
		
		state.moveSnake();
		state.moveSnake();
		
		System.out.println(state.getBoard().toString());
		
		assertEquals(state.getBody().getHead().col, headOldCol+2);
		assertEquals(state.getBody().getHead().row, headOldRow);
		assertEquals(state.getBody().getTail().col, tailOldCol+2);
		assertEquals(state.getBody().getTail().row, tailOldRow);
		assertEquals(2, state.getBody().getBody().size());
	}
	
	
	@Test
	public void testMove2() {
		State state = new State();
		int headOldRow = state.getBody().getHead().row;
		int headOldCol = state.getBody().getHead().col;
		int tailOldRow = state.getBody().getTail().row;
		int tailOldCol = state.getBody().getTail().col;
		
		state.turnDown();
		state.moveSnake();
		
		assertEquals(state.getBody().getHead().row, headOldRow+1);
		assertEquals(state.getBody().getHead().col, headOldCol);
		assertEquals(state.getBody().getTail().row, tailOldRow);
		assertEquals(state.getBody().getTail().col, tailOldCol+1);
	}
}
