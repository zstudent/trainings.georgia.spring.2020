package Snake;

import java.awt.*;
//Cell is kindofa board component.
//In our current example it doesn't have additional features but it can.
public class Cell extends BoardComponent {
    private Color color;

    public Cell(int row, int col, Color color) {
        super(row, col);
        setColor(color);
    }

    //Copy constructor
    public Cell(Cell cell)
    {
        super(cell.getRow(),cell.getCol());
        setColor(cell.color);
    }

    public Color getColor() { return color; }

    public void setColor(Color color)
    {
        this.color = color;
    }
}

