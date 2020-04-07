package lesson200407;

import java.util.Iterator;

public class UseLinkedList {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add("one");
		list.add("two");
		list.add("three");
		
		System.out.println(list);
		
		int[] arr = {10,20,30};
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		LinkedList empty = new LinkedList();
		Iterator<String> emptyIt = empty.iterator();
		emptyIt.next();
		
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		for (String item : list) {
			System.out.println(item);
		}
	}
	
	

}
