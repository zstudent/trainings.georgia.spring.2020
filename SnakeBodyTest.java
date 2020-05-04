import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SnakeBodyTest {
	
	@Test
	public void testConstructor() {
		SPoint[] points = {new SPoint(2,4)};
		SnakeBody snakeBody = new SnakeBody(points);
		assertNotNull(snakeBody.getBody());
	}
	
	
	@Test
	public void testHeadAndTail() {
		SPoint[] points = {new SPoint(2,4), new SPoint(2,3), new SPoint(2,1)};
		SnakeBody snakeBody = new SnakeBody(points);
		assertEquals(snakeBody.getHead(), new SPoint(2,4));
		assertEquals(snakeBody.getTail(), new SPoint(2,1));
	}
	
	
	@Test
	public void testGrow() {
		SPoint pt1 = new SPoint(2,4);
		SPoint pt2 = new SPoint(2,5);
		SPoint pt3 = new SPoint(1,5);
		SPoint[] points = {pt1};
		SnakeBody snakeBody = new SnakeBody(points);
		
		assertTrue(snakeBody.getHead().equals(pt1));
		assertTrue(snakeBody.getTail().equals(pt1));
		snakeBody.grow();
		assertTrue(snakeBody.getHead().equals(pt2));
		assertTrue(snakeBody.getTail().equals(pt1));
		snakeBody.currDirrection = "Up";
		snakeBody.grow();
		assertTrue(snakeBody.getHead().equals(pt3));
		assertTrue(snakeBody.getTail().equals(pt1));
	}
	
	
	@Test
	public void testMove() {
		SPoint pt1 = new SPoint(2, 4);
		SPoint pt2 = new SPoint(2, 5);
		SPoint pt3 = new SPoint(3, 5);
		SPoint[] points = {pt1};
		SnakeBody sb = new SnakeBody(points);
		
		sb.move();
		assertTrue(sb.getHead().equals(pt2));
		assertTrue(sb.getTail().equals(pt2));
		assertEquals(1, sb.getBody().size());
		
		sb.currDirrection = "Down";
		sb.move();
		assertTrue(sb.getHead().equals(pt3));
		assertTrue(sb.getHead().equals(sb.getTail()));
	}
	
	
	@Test
	public void testMove2() {
		SPoint pt1 = new SPoint(2, 4);
		SPoint pt2 = new SPoint(2, 3);
		SPoint pt3 = new SPoint(2, 5);
		SPoint[] points = {pt1, pt2};
		SnakeBody sb = new SnakeBody(points);
		
		sb.move();
		assertTrue(sb.getHead().equals(pt3));
		assertTrue(sb.getTail().equals(pt1));
		assertEquals(sb.getBody().size(), 2);
	}
}
