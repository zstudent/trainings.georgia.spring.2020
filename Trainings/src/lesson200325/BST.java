//package lesson200325;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST implements Iterable<String>{
	
	private static class Node {
		private String key;
		private String value;
		private Node left, right;
		public Node(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
	private Node root;
	
	public void put(String key, String value) {
		root = put(root, key, value);
	}
	public String get(String key){return get(root,key);}

	private String get(Node x, String key){
		if(x == null){
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0){
			return x.value;
		}
		if(cmp > 0){
			return get(x.right, key);
		}
		return get(x.left, key);
	}

	private Node put(Node x, String key, String value) {
		if (x == null) return new Node(key, value);
		
		int cmp = key.compareTo(x.key);
		if (cmp < 0) 
			x.left = put(x.left, key, value);
		else if (cmp > 0) 
			x.right = put(x.right, key, value);
		else x.value = value;
		
		return x;
	}

	@Override
	public Iterator<String> iterator() {
		Queue<String> q = new LinkedList<String>();
		inOrder(root, q);
		return q.iterator();
	}

	private void inOrder(Node x, Queue<String> q) {
		if (x == null) return;
		inOrder(x.left, q);
		q.add(x.key);
		inOrder(x.right, q);
	}

}
