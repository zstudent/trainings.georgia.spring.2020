package Snake;

import java.awt.*;

//BoardComponent is everything that'll need x,y in game.
public class BoardComponent {
    private int row;
    private int col;

    public BoardComponent(int row,int col)
    {
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setComponent(BoardComponent component)
    {
        this.row = component.row;
        this.col = component.col;
    }
}
