import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void getNumberOfNodes() {
        Graph g = new Graph(10);
        assertEquals(10 , g.getNumberOfNodes());
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        Graph g = new Graph(10);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,6);
        List<Integer> res = g.getNeighbors(1);
        assertTrue(res.containsAll(Arrays.asList(2,3,6)));
        res = g.getNeighbors(3);
        assertTrue(res.containsAll(Arrays.asList(1)));
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        Graph g = new Graph(10);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,6);
        g.removeEdge(6,1);
        List<Integer> res = g.getNeighbors(1);
        assertTrue(res.containsAll(Arrays.asList(2,3)));
    }

}