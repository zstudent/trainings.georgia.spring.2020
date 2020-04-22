import java.util.LinkedList;

public class Body {
    public LinkedList <Node> bodyList;
    public Direction currentDirection;
    Node head;

    public Body(Node start){
        bodyList = new LinkedList<>();
        bodyList.add(start);
        head = start;
        currentDirection = Direction.RIGHT;
    }

    public void grow(){
        Node n  = nextHead();
        bodyList.addFirst(n);
        head = n;
    }

    public void move(){
        grow();
        bodyList.removeLast();
    }

    public void changeDirection(Direction d){
        currentDirection = d;
    }


    public Node nextHead(){
        int row = head.getRow();
        int column = head.getColumn();
        if (currentDirection == Direction.LEFT){
            column--;
        }else if(currentDirection == Direction.RIGHT){
            column++;
        }else if(currentDirection == Direction.DOWN){
            row++;
        }else if(currentDirection == Direction.UP){
            row--;
        }
        return new Node(row, column);
    }

    public int size(){return bodyList.size();}

    public Node getHead(){
        return head;
    }

    public Node getTail(){return bodyList.getLast();}



}
