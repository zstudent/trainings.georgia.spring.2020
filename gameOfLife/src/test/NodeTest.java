import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    @Test
    public void testConstructor(){
        Node n = new Node(1,2);
        assertEquals(1, n.getX());
        assertEquals(2, n.getY());
    }

    @Test
    public void testEquals(){
        Node n1 = new Node(1, 2);
        Node n2 = new Node(1, 2);
        Node n3 = new Node(2, 2);
        assertEquals(n1, n2);
        assertNotEquals(n1, n3);
        assertNotEquals(n1, "BAD CASE");
    }

    @Test
    public void testNeighbours(){
        Node[] arr = new Node[]{
                new Node(2,3),
                new Node(2,4),
                new Node(2,5),
                new Node(3,3),
                new Node(3,5),
                new Node(4, 3),
                new Node(4,4),
                new Node(4,5)
        } ;
        Node n = new Node(3,4);
        assertArrayEquals(arr, n.neighbours().toArray());
    }

    @Test
    public void testNeighbours2(){
        Node[] arr = new Node[]{
                new Node(0,1),
                new Node(1,0),
                new Node(1,1)
        } ;
        Node n = new Node(0,0);
        assertArrayEquals(arr, n.neighbours().toArray());
    }

    @Test public void testToString(){
        Node n = new Node(1,2);
        assertEquals("(1,2)", n.toString());
    }

    @Test public void testHashCode(){
        Node n = new Node(1,2);
        assertEquals(13, n.hashCode());

    }

}