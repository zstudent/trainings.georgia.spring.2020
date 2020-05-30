import static org.junit.Assert.*;

public class NodeTest {

    @org.junit.Test
    public void getRow() {
        for(int i = 0; i < 100; i++){
            Node n = new Node(i,0);
            assertEquals(i, n.getRow());
        }
    }

    @org.junit.Test
    public void getColumn() {
        for(int i = 0; i < 100; i++){
            Node n = new Node(0,i);
            assertEquals(i, n.getColumn());
        }
    }

    @org.junit.Test
    public void testEquals() {
        Node n1 = new Node(1,2);
        Node n2 = new Node(1,2);
        Node n3 = new Node(2,1);
        assertTrue(n1.equals(n2));
        assertFalse(n3.equals(n1));
    }
}