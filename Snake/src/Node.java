import java.awt.*;

public class Node {
    private int row;
    private int column;

    public Node(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public boolean equals(Node n){
        return column==n.getColumn() && row==n.getRow();
    }


}
