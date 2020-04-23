package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private Cell snakeHead;
    private Cell snakeTail;
    private LinkedList<Cell> snakeBody;

    public Snake(int row, int col)
    {
        snakeHead = new Cell(row,col,Color.RED);
        snakeTail = snakeHead;
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

    public Cell getTail() { return this.snakeTail; }

    public void setTail(Cell newTail) { this.snakeTail = newTail; }

    public void growSnake(int direction){
        int row = snakeTail.getRow();
        int col = snakeTail.getCol();
        if( direction == 1) col++;
        else if (direction == -1) col--;
        else if (direction == 2) row--;
        else if (direction == -2) row++;
        Cell c = new Cell(row,col,Color.RED);
        this.snakeBody.add(c);
        snakeTail = c;
    }

}
