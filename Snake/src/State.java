import java.util.Random;

public class State {
    private int ROWS ;
    private int COLUMNS ;
    public Board board;
    public Body body;
    public boolean gameOver = false;
    public int score;

    public State(int rows, int columns){
        score = 0;
        this.ROWS = rows;
        this.COLUMNS = columns;
        board = new Board(ROWS,COLUMNS);
        body = new Body(new Node(10,15));
        body.grow();
    }

    public void startGame(){
        board.setBoard(body.head.getRow(),body.head.getColumn(), 1);
        generateFruit();
    }

    public void changeDirection(Direction d){
        Direction old = body.currentDirection;
        if(old == Direction.UP && d!=Direction.DOWN){
            body.changeDirection(d);
        }else if(old == Direction.DOWN && d!=Direction.UP){
            body.changeDirection(d);
        }else if(old == Direction.RIGHT && d!=Direction.LEFT){
            body.changeDirection(d);
        }else if(old == Direction.LEFT && d!=Direction.RIGHT){
            body.changeDirection(d);
        }
    }

    public void generateFruit(){
        Random r = new Random();
        int row = 0;
        int column = 0;
        int check = -1;
        while (check!=0){
            row = r.nextInt(ROWS);
            column = r.nextInt(COLUMNS);
            check = board.getBoard(row,column);
        }
        board.setBoard(row,column,2);
    }

    public void move(){
        Node n = body.nextHead();
        int check = board.getBoard(n.getRow(),n.getColumn());
        if(check == 1){
            gameOver=true;
            board.setBoard(body.head.getRow(), body.head.getColumn(),3);
            return;
        }
        if(check == 2){
            score+=body.size()*10;
            body.grow();
            board.setBoard(body.head.getRow(),body.head.getColumn(), 1);
            generateFruit();
        }else{
            Node tail = body.getTail();
            board.setBoard(tail.getRow(),tail.getColumn(), 0);
            body.move();
            board.setBoard(body.head.getRow(),body.head.getColumn(), 1);
        }
    }
}
