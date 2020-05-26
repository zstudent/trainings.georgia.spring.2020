import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {
    @Test
    public void testConstructor(){
        State state = new State();
        Node n = new Node(1,2);
        assertNull(state.nCount.get(n));
        assertEquals(0, state.board.size());
    }

    @Test
    public void testIncrease(){
        State state = new State();
        Node n = new Node(1,2);
        state.increase(n);
        Integer i = 1;
        assertEquals(i, state.nCount.get(n));
        state.increase(n);
        i++;
        assertEquals(i, state.nCount.get(n));
    }

    @Test
    public void testDecrease(){
        State state = new State();
        Node n = new Node(1,2);
        state.increase(n);
        state.increase(n);
        Integer i = 2;
        assertEquals(i, state.nCount.get(n));
        state.decrease(n);
        i--;
        assertEquals(i, state.nCount.get(n));
        state.decrease(n);
        assertNull(state.nCount.get(n));
    }

    @Test
    public void timePass1(){
        Node n = new Node(1,0);
        State state = new State();
        state.revive(n);
        assertEquals(1, state.board.size());
        state.timePasses();
        assertEquals(0, state.board.size());
    }

    @Test
    public void timePass2(){
        Node n = new Node(1,0);
        Node n2 = new Node(1,1);
        State state = new State();
        state.revive(n);
        state.revive(n2);
        assertEquals(2, state.board.size());
        state.timePasses();
        assertEquals(0, state.board.size());
    }

    @Test
    public void timePass3(){
        Node n = new Node(1,0);
        Node n2 = new Node(1,1);
        Node n3 = new Node(0,1);
        State state = new State();
        state.revive(n);
        state.revive(n2);
        state.revive(n3);
        assertEquals(3, state.board.size());
        state.timePasses();
        assertEquals(4, state.board.size());
    }

    @Test
    public void timePass4(){
        Node n = new Node(1,0);
        Node n2 = new Node(1,1);
        Node n3 = new Node(0,1);
        Node n4 = new Node(0,0);
        State state = new State();
        state.revive(n);
        state.revive(n2);
        state.revive(n3);
        state.timePasses();
        assertEquals(9, state.nCount.size());
        assertEquals((Integer) 3, state.nCount.get(n));
        assertEquals((Integer) 3, state.nCount.get(n2));
        assertEquals((Integer) 3, state.nCount.get(n3));
        assertEquals((Integer) 3, state.nCount.get(n4));
    }

}