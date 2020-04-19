package lesson200407;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestStack {
	Stack list = new Stack();
	
	public void pushElements(Stack list) {
		String[] arr = {"Box1", "Box2", "Box3"};
		
		for (int i = 0; i < arr.length; i++) {
			list.push(arr[i]);
		}
	}

	@Test
	void test_pop() {
		pushElements(list);
		assertEquals("Box3", list.pop());
	}
	
	@Test
	void test_top() {
		pushElements(list);
		assertEquals("Box3", list.top());
	}

}
