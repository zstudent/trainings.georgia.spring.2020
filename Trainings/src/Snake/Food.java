package Snake;

import java.awt.*;
//
public class Food extends BoardComponent{
    private Color color;
    //Could have different food's needed specifications.
    public Food(int row, int col, Color color) {
        super(row, col);
        setColor(color);
    }
    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }
}
