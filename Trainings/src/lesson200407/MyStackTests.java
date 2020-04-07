package lesson200407;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/*
 * 	Tests for MyStack class.
 */
class MyStackTests {
	
	/*
	 * 	Testing getSize(), pop(), push().
	 */
	@Test
	void testing() {
		MyStack<String> myStack = new MyStack();	//Creating MyStack object.
		Stack<String> stack = new Stack();	//Creating original Stack object.
		
		assertEquals( stack.size(), myStack.getSize() );	//Sizes should be equal.
		
		String [] array = {"a" , "b", "c", "d", "e","f","g"};
		for(String str : array)
		{
			//put elements in both stacks.
			stack.push(str);
			myStack.push(str);
		}
		
		assertEquals( stack.size(), myStack.getSize());	//make sure everything added correctly.
		
		while( !stack.isEmpty() )
		{	
			String myResult = myStack.pop();
			String result =  stack.pop();
			assertEquals( myResult, result );	//Both popped elements should be equal.
		}
		
		assertEquals( stack.size(), myStack.getSize() );	//Sizes should be equal.
		
		
	}
	


}
