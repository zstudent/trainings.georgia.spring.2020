import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class Graph_test {
	@Test
	public void test1() {
		Graph n = new Graph(0);
		assertEquals(0,n.numVerticies());
	}
	public void test2() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		LinkedList<Integer> temp = n.neighbours(1);
		assertEquals(2,temp.get(0));
	}
	public void test3() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		n.addEdge(1, 3);
		n.addEdge(1, 4);
		n.addEdge(1, 5);
		LinkedList<Integer> temp = n.neighbours(1);
		assertEquals(4,temp.size());
	}
	public void test4() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		n.addEdge(1, 3);
		n.addEdge(1, 4);
		n.addEdge(1, 5);
		LinkedList<Integer> temp = n.neighbours(1);
		assertEquals(2,temp.get(0));
	}
	public void test5() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		n.addEdge(1, 3);
		n.addEdge(1, 4);
		n.addEdge(1, 5);
		LinkedList<Integer> temp = n.neighbours(1);
		assertEquals(3,temp.get(1));
	}
	public void test6() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		n.addEdge(1, 3);
		n.addEdge(1, 4);
		n.addEdge(1, 5);
		LinkedList<Integer> temp = n.neighbours(2);
		assertEquals(1,temp.size());
	}
	public void test7() {
		Graph n = new Graph(10);
		n.addEdge(1, 2);
		n.addEdge(1, 3);
		n.addEdge(1, 4);
		n.addEdge(1, 5);
		LinkedList<Integer> temp = n.neighbours(1);
		assertEquals(1,temp.get(5));
	}
	public void test8() {
		Graph n = new Graph(10);
		n.addEdge(0, 1);
		n.addEdge(1, 2);
		n.addEdge(2, 3);
		n.addEdge(4, 5);
		LinkedList<Integer> temp = n.neighbours(0);
		assertEquals(1,temp.get(5));
	}

}
