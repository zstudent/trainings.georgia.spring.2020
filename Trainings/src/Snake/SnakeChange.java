package Snake;

import java.awt.*;
import java.util.LinkedList;

//stateless class/bean.
public class SnakeChange {
    //Move snake with deltaRow and deltaCol
    public static void moveSnake(Board board,Snake snake,int deltaRow,int deltaCol) {
        int newRow = snake.getRow() + deltaRow;
        int newCol = snake.getCol() + deltaCol;
        if (newCol < 0) newCol = board.getNumCols() - 1;
        if (newRow < 0) newRow = board.getNumRows() - 1;
        if (newRow > board.getNumRows() - 1) newRow = 0;
        if (newCol > board.getNumCols() - 1) newCol = 0;
        LinkedList<Cell> body = snake.getSnakeBody();
        //If body is empty modify only head of snake.
        if (body.isEmpty()) {
            board.setColor(snake.getRow(), snake.getCol(), Color.BLACK);
            snake.setHeadCol(newCol);
            snake.setHeadRow(newRow);
            board.setColor(snake.getHeadRow(), snake.getHeadCol(), Color.RED);
        } else {
            //If snake has body, removeLast cell, add new one in front.
            Cell newHead = new Cell(newRow, newCol, Color.RED);
            board.setColor(body.getLast().getRow(), body.getLast().getCol(), Color.BLACK);
            snake.getSnakeBody().removeLast();
            snake.getSnakeBody().addFirst(new Cell(snake.getHeadRow(), snake.getHeadCol(), snake.getColor()));
            snake.setComponent(newHead);
            board.setColor(newHead.getRow(), newHead.getCol(), Color.RED);
        }
    }

    //Grow snake
    public static void growSnake(Snake snake,Board board,int direction){
        int r = 0;
        int c = 0;
        if(snake.getSnakeBody().isEmpty())
        {
            r = snake.getRow();
            c = snake.getCol();
        }
        else{
            r = snake.getSnakeBody().getLast().getRow();
            c = snake.getSnakeBody().getLast().getCol();
        }
        if( direction == 1) c++;
        else if (direction == -1) c--;
        else if (direction == 2) r--;
        else if (direction == -2) r++;
        //This next part is needed as growth may have bugs ,
        //When snake's body is on the border of board
        //and snake in specific direction eats an apple.
        if (c < 0) c = board.getNumCols() - 1;
        if (r < 0) r = board.getNumRows() - 1;
        if (r > board.getNumRows() - 1) r = 0;
        if (c > board.getNumCols() - 1) c = 0;
        Cell cell = new Cell(r,c,Color.RED);
        snake.getSnakeBody().addLast(cell);
    }
}
