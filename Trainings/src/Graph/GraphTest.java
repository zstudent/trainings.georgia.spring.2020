package Graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	void test() {
		Graphs g = new Graphs(5);
		g.addEdge(0, 1);
		g.addEdge(2, 3);
		g.addEdge(1, 4);
		System.out.println(g.toString());
		
		assertTrue( GraphSearch.DFS(0, 4, g) );
		assertFalse( GraphSearch.DFS(0, 2, g) );
		assertTrue( GraphSearch.DFS(1, 4, g) );
	}

}
