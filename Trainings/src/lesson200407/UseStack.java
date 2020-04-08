package lesson200407;

import java.util.Iterator;

public class UseStack {

	public static void main(String[] args) {
		Stack list = new Stack();

		//push elements in stack
		String[] arr = {"Box1", "Box2", "Box3"};
	
		for (int i = 0; i < arr.length; i++) {
			list.push(arr[i]);
		}
		
		//traverse 
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		//pop last element and return its value
		System.out.println("Pop element: " + list.pop());
		
		//get top element of stack
		System.out.println("Top element after poping: " + list.top());
	}
	
	

}
