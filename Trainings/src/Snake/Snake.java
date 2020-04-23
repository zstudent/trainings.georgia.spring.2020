package Snake;

import java.awt.*;

public class Snake {
    private Cell snakeHead;

    public Snake(int row, int col)
    {
        snakeHead = new Cell(row,col,Color.RED);
    }

    public Cell getSnakeHead()
    {
        return this.snakeHead;
    }

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
}
