import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testConstructor() {
		Board board = new Board(20, 25);
		assertNotNull(board.grid);
		assertEquals(board.height, 20);
		assertEquals(board.width, 25);
	}
	
	
	@Test
	public void testAddBodyAndUndo1() {
		Board board = new Board(20, 24);
		SnakeBody snakeBody = new SnakeBody(new SPoint[] {new SPoint(4, 6), new SPoint(4, 5), new SPoint(4, 4)});
		String beforeAdding = board.toString();
		
		int addingRes = board.addSnakeBody(snakeBody);
		
		assertTrue(board.getGrid(4, 4));
		assertTrue(board.getGrid(4, 6));
		assertFalse(board.getGrid(4, 7));
		assertEquals(Board.ADD_OK, addingRes);
		
		board.undoSnakeBody();
		
		assertEquals(beforeAdding, board.toString());
		assertFalse(board.getGrid(4, 4));
	}
	
	
	@Test
	public void testAddBodyAndUndo2() {
		Board board = new Board(10, 10);
		SnakeBody snakeBody = new SnakeBody(new SPoint[] {new SPoint(-1, 0), new SPoint(0, 0)});
		int addingRes = board.addSnakeBody(snakeBody);
		String beforeAdding = board.toString();
		
		assertEquals(Board.ADD_BAD, addingRes);
		board.undoSnakeBody();
		assertEquals(beforeAdding, board.toString());
	}
	
	
	@Test
	public void testAddBodyAndUndo3() {
		Board board = new Board(20, 24);
		SnakeBody snakeBody = new SnakeBody(new SPoint[] {new SPoint(4, 6), new SPoint(4, 5), new SPoint(4, 4), new SPoint(4, 6)});
		String beforeAdding = board.toString();
		
		int addingRes = board.addSnakeBody(snakeBody);
		
		assertEquals(Board.ADD_BAD, addingRes);
		board.undoSnakeBody();
		assertEquals(beforeAdding, board.toString());
	}
	
	
}
