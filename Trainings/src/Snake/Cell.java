package Snake;

import java.awt.*;

public class Cell {
    private int row;
    private int col;
    private Color color;

    public Cell(int row,int col,Color color)
    {
        this.color = color;
        this.row = row;
        this.col = col;
    }


    public void setCol(int col) {
        this.col = col;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Color getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
