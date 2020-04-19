package lesson200407;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<String> implements Iterable<String> {
	private int n;
	private Node head;
	
	private class Node {
		private String value;
		private Node next;
	}
	
	public LinkedStack() {
		head = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return n;
	}
	
	public void push(String value) {
		Node oldHead = head;
		head = new Node();
		head.next = oldHead;
		head.value = value;
		n++;
	}
	
	public String peek() {
		if (isEmpty()) throw new NoSuchElementException();
		return head.value;
	}
	
	public String pop() {
		if (isEmpty()) throw new NoSuchElementException();
		String value = head.value;
		head = head.next;
		n--;
		return value;
	}
	
	public java.lang.String toString() {
        StringBuilder sb = new StringBuilder();
        for (String value : this)
        	sb.append('[').append(value).append("] ");
        return sb.toString();
    }
	
	public Iterator<String> iterator() {
		return new LinkedIterator();
	}
    
	private class LinkedIterator implements Iterator<String> {
		private Node current = head;
		public boolean hasNext() { return current != null; }
		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			String value = current.value;
			current = current.next;
			return value;
		}
	}
}
