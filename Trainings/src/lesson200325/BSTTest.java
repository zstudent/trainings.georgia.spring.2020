import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @org.junit.jupiter.api.Test
    void get() {
        BST bst = new BST();
        bst.put("Bob", "Bob");
        bst.put("Sue", "Sue");
        bst.put("Mike", "Mike");
        bst.put("Alice", "Alice");
        bst.put("Ann", "Ann");
        assertEquals("Bob", bst.get("Bob"));
        assertEquals("Sue", bst.get("Sue"));
        assertEquals("Mike", bst.get("Mike"));
        assertEquals("Alice", bst.get("Alice"));
        assertEquals("Ann", bst.get("Ann"));
        assertEquals(null, bst.get("Gela"));
    }
}