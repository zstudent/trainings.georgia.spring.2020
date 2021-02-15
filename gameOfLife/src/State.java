import java.lang.reflect.Array;
import java.util.*;

public class State {
    public static final int NODE_COUNT = 2500;
    protected HashSet<Node> board ;
    //number of live neighbours for each Node
    protected HashMap<Node, Integer> nCount;

    public State(){
        board = new HashSet<>(NODE_COUNT);
        nCount = new HashMap<>(NODE_COUNT);
    }

    public void clicked(Node n){
        if(board.contains(n)){
            kill(n);
        }else{
            revive(n);
        }
    }

    public void increase(Node n){
        if(nCount.get(n) == null){
            nCount.put(n, 1);
            return;
        }
        nCount.put(n, nCount.get(n)+1);
    }

    public void decrease(Node n){
        int k = nCount.get(n) - 1;
        if(k==0){
            nCount.remove(n);
            return;
        }
        nCount.put(n, k);
    }

    public void revive(Node n){
        n.neighbours().forEach(this::increase);
        board.add(n);
    }

    public void kill(Node n){
        n.neighbours().forEach(this::decrease);
        board.remove(n);
    }

    private List<Node> checkForKill(){
        List<Node> list = new ArrayList<>();
        for(Node n : board){
            Integer k = nCount.get(n);
            if(k == null || k < 2 || k > 3){
                list.add(n);
            }
        }
        return list;
    }

    private List<Node> checkForRevive(){
        List<Node> list = new ArrayList<>();
        for(Node n : nCount.keySet()){
            if(nCount.get(n) == 3 && !board.contains(n)){
                list.add(n);
            }
        }
        return list;
    }

    public void timePasses(){
        List<Node> revive = checkForRevive();
        List<Node> kill = checkForKill();
        revive.forEach(this::revive);
        kill.forEach(this::kill);
    }

    public void gliderGun() {
        revive(new Node(3, 11));
        revive(new Node(2, 12));
        revive(new Node(24, 12));
        revive(new Node(3, 12));
        revive(new Node(26, 12));
        revive(new Node(26, 13));
        revive(new Node(14, 9));
        revive(new Node(13, 10));
        revive(new Node(36, 9));
        revive(new Node(15, 9));
        revive(new Node(12, 11));
        revive(new Node(37, 9));
        revive(new Node(36, 10));
        revive(new Node(12, 12));
        revive(new Node(37, 10));
        revive(new Node(12, 13));
        revive(new Node(17, 10));
        revive(new Node(16, 12));
        revive(new Node(13, 14));
        revive(new Node(18, 11));
        revive(new Node(22, 9));
        revive(new Node(24, 8));
        revive(new Node(26, 7));
        revive(new Node(18, 12));
        revive(new Node(23, 9));
        revive(new Node(22, 10));
        revive(new Node(14, 15));
        revive(new Node(19, 12));
        revive(new Node(26, 8));
        revive(new Node(18, 13));
        revive(new Node(23, 10));
        revive(new Node(15, 15));
        revive(new Node(17, 14));
        revive(new Node(22, 11));
        revive(new Node(23, 11));
        revive(new Node(2, 11));
    }

    public void random(){
        board = new HashSet<>(NODE_COUNT);
        for(int i = 0; i<50;i++){
            for(int j = 0; j < 50;j++) {
                if (Math.random() < 0.5) {
                    revive(new Node(i,j));
                }
            }
        }
    }
}
