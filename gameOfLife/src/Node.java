import java.util.ArrayList;
import java.util.List;

public class Node {
    private int x;
    private int y;

    //hash coefficients
    private final int xCoef = 3;
    private final int yCoef = 5;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public List<Node> neighbours(){
        List<Node> list = new ArrayList<>();
        for(int i = -1 ; i <= 1 ; i++){
            for(int j = -1 ; j<=1; j++){
                if(i!=0 || j!=0){
                    if(x+i>=0 && y+j>=0){
                        list.add(new Node(x+i,y+j));
                    }
                }
            }
        }
        return list;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o){
       if (getClass() != o.getClass()){return false;}
       Node n = (Node)o;
       return y == n.getY() && x == n.getX();
    }

    @Override
    public int hashCode(){
        return x * xCoef + y * yCoef;
    }

    @Override
    public String toString(){
        return "("+ x + "," + y + ")";
    }
}
