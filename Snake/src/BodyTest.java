import org.junit.Test;

import static org.junit.Assert.*;

public class BodyTest {

    @Test
    public void TestConstructor() {
        Node n = new Node(1,5);
        Body body = new Body(n);
        assertTrue(n.equals(body.getHead()));
        assertTrue(n.equals(body.getTail()));
    }

    @Test
    public void TestGrow() {
        Node n = new Node(7,5);
        Body body = new Body(n);
        body.changeDirection(Direction.UP);
        body.grow();
        Node n1 = new Node(6,5);
        assertTrue(n1.equals(body.getHead()));
        body.changeDirection(Direction.DOWN);
        body.grow();
        assertTrue(n.equals(body.getHead()));
        body.changeDirection(Direction.RIGHT);
        body.grow();
        n1 = new Node(7,6);
        assertTrue(n1.equals(body.getHead()));
        body.changeDirection(Direction.LEFT);
        body.grow();
        assertTrue(n.equals(body.getHead()));
        assertEquals(5,body.size());
    }

    @Test
    public  void testMove(){
        Node n = new Node(5,6);
        Body body = new Body(n);
        body.grow();
        body.grow();
        Node h1 = new Node(5,8);
        Node t1 = new Node(5,6);
        assertTrue(h1.equals(body.getHead()));
        assertTrue(t1.equals(body.getTail()));
        body.move();
        h1 = new Node(5,9);
        t1 = new Node(5,7);
        assertTrue(h1.equals(body.getHead()));
        assertTrue(t1.equals(body.getTail()));
    }

}