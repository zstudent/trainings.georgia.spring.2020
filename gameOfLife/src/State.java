import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

}
