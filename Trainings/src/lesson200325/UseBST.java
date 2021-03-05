//package lesson200325;

public class UseBST {
	
	public static void main(String[] args) {
		BST bst = new BST();
		
		bst.put("Bob", "Bob");
		bst.put("Sue", "Sue");
		bst.put("Mike", "Mike");
		bst.put("Alice", "Alice");
		bst.put("Ann", "Ann");
		
		for (String key : bst) {
			System.out.println(key);
		}
	}

}
