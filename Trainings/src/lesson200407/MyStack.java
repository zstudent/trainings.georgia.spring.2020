package lesson200407;

import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack <T>  implements Iterable<T>
{	
	//Inner class of Node.
	private class Node<T>
	{
		private Node next;
		private T value;
		
		//Constructor for Node class.
		public Node( T value,Node next )
		{
			this.next = next;
			this.value = value;
		}
	}
	
	//Pointer to the first element.
	private Node head;
	
	//size of our stack.
	private int size;
	
	//Constructor
	public MyStack()
	{
		this.head = null;
		this.size = 0;
	}
	
	//Push method of MyStack class.
	public void push(T value)
	{
		this.head = new Node(value,head);
		this.size++;
	}
	
	//Gets value of top element of stack, but doesn't delete it.
	public T peek() throws EmptyStackException
	{
		if( this.isEmpty() ) throw new EmptyStackException();
		return (T) head.value;
	}
	
	//Gets value of top element of stack, and deletes it.
	public T pop() throws EmptyStackException
	{
		if( this.isEmpty() ) throw new EmptyStackException();
		T res = (T) head.value;
		head = head.next;
		this.size--;
		return res;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	//returns true if stack's size is 0.
	public boolean isEmpty()
	{
		return size == 0;
	}

	
	//Implementing iterator for MyStack.
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node pointer = head;
			@Override
			public boolean hasNext() {
				return pointer != null;
			}

			@Override
			public T next() {
				if(! hasNext() ) return null;
				T result = (T) pointer.value;
				pointer = pointer.next;
				return result;
			}
		};
	}

	
	
	
}
