import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class GraphTest {
	
	@Test
	public void test() {
		Graph graph = new Graph(4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(0, 3);
		
		Stack<Integer> firstRes = new Stack<Integer>();
		firstRes.push(1); firstRes.push(3);
		
		Stack<Integer> secRes = new Stack<Integer>();
		secRes.push(0); secRes.push(2);
		
		Stack<Integer> thirdRes = new Stack<Integer>();
		thirdRes.push(1); thirdRes.add(3);
		
		Stack<Integer> res4 = new Stack<Integer>();
		res4.push(2); res4.push(0);
		
		assertTrue(graph.adj(0).equals(firstRes));
		assertTrue(graph.adj(1).equals(secRes));
		assertTrue(graph.adj(2).equals(thirdRes));
		assertTrue(graph.adj(3).equals(res4));
		assertEquals(4, graph.getNumV());
	}

}
