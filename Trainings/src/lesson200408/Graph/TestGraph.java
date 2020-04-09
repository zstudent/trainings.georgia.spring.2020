package lesson200408;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGraph {
	
	Graph gr = new Graph(5);
	
	void setUp() {
		gr.addEdge(0, 1);
		gr.addEdge(0, 2);
		gr.addEdge(0, 3);
		gr.addEdge(0, 4);
		gr.addEdge(1, 2);
		gr.addEdge(1, 3);
		gr.addEdge(1, 4);
	}

	@Test
	void test0() {
		setUp();
		assertEquals(4, gr.degree(0));
	}
	
	@Test
	void test1() {
		setUp();
		assertEquals(4, gr.degree(1));
	}
	
	@Test
	void test2() {
		setUp();
		assertEquals(2, gr.degree(2));
	}
	
	@Test
	void test3() {
		setUp();
		assertEquals(2, gr.degree(3));
	}
	
	@Test
	void test4() {
		setUp();
		assertEquals(2, gr.degree(4));
	}


}
