package lesson200407;

import java.util.Iterator;

public class LinkedList  implements Iterable<String>{
	
	private static class Node {
		Node next;
		String value;
		public Node(Node next, String value) {
			this.next = next;
			this.value = value;
		}
	}

	private Node head = null;
	
	public void add(String item) {
		head = new Node(head, item);
	}
	
	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		Node current = head;
//		
//		while (current != null) {
//			sb.append('[').append(current.value).append("] ");
//			current = current.next;
//		}
//		
//		return sb.toString();

		StringBuilder sb = new StringBuilder();
		Iterator<String> it = iterator();
		while (it.hasNext()) {
			sb.append('[').append(it.next()).append("] ");
		}
		return sb.toString();
		
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public String next() {
				if (!hasNext()) return null;
				String value = current.value;
				current = current.next;
				return value;
			}
		};
	}
}
















