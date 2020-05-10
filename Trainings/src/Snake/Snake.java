package Snake;

import java.awt.*;
import java.util.LinkedList;

//Snake(specifically snake's head) is a board component.
public class Snake extends BoardComponent{
    private LinkedList<Cell> snakeBody;
    Color color;

    public Snake(int row, int col)
    {
        super(row,col);
        snakeBody = new LinkedList<Cell>();
    }
    public int getHeadRow()
    {
        return getRow();
    }

    public int getHeadCol()
    {
        return getCol();
    }

    public void setHeadCol(int col)
    {
        setCol(col);
    }

    public void setHeadRow(int row)
    {
        setRow(row);
    }

    public LinkedList<Cell> getSnakeBody(){ return this.snakeBody; }

    public void setSnakeBody(LinkedList<Cell> body){ this.snakeBody = body; }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
