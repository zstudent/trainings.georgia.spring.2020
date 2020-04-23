package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private Cell snakeHead;
    //private Cell snakeTail;
    private LinkedList<Cell> snakeBody;

    public Snake(int row, int col)
    {
        snakeHead = new Cell(row,col,Color.RED);
        //snakeTail = snakeHead;
        snakeBody = new LinkedList<Cell>();
    }

    public Cell getSnakeHead()
    {
        return this.snakeHead;
    }

    public LinkedList<Cell> getSnakeBody(){ return this.snakeBody; }

    public int getHeadRow()
    {
        return snakeHead.getRow();
    }

    public int getHeadCol()
    {
        return snakeHead.getCol();
    }

    public void setHeadCol(int col)
    {
        this.snakeHead.setCol(col);
    }

    public void setHeadRow(int row)
    {
        this.snakeHead.setRow(row);
    }

    public void setHead(Cell newHead){ this.snakeHead = newHead; }

   // public Cell getTail() { return this.snakeTail; }

    //public void setTail(Cell newTail) { this.snakeTail = newTail; }

    public void growSnake(int direction){
        int r = 0;
        int c = 0;
        if(snakeBody.isEmpty())
        {
            r = snakeHead.getRow();
            c = snakeHead.getCol();
        }
        else{
            r = snakeBody.getLast().getRow();
            c = snakeBody.getLast().getCol();
        }
        if( direction == 1) c++;
        else if (direction == -1) c--;
        else if (direction == 2) r--;
        else if (direction == -2) r++;
        Cell cell = new Cell(r,c,Color.RED);
        snakeBody.addLast(cell);
    }

}
