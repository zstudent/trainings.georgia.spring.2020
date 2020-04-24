package Snake;

import java.awt.*;

public class Apple {
    private Cell apple;

    public Apple(int row,int col)
    {
        apple = new Cell(row, col,Color.GREEN);
    }

    public int getRow()
    {
        return apple.getRow();
    }

    public int getCol()
    {
        return apple.getCol();
    }

    public Color getColor()
    {
        return apple.getColor();
    }

    public void setRow(int row)
    {
        apple.setRow(row);
    }

    public void setCol(int col)
    {
        apple.setCol(col);
    }

    public void setColor(Color color)
    {
        apple.setColor(color);
    }

}
