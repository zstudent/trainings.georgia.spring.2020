package Snake;

import java.awt.*;

public class Apple extends Food{
    //In this example we don't have details for apple,
    //but we could maybe put icon of apple here,
    //and if we wanted later to add another food called Pear. pear would have it's own specifications.
    public Apple(int row, int col, Color color) {
        super(row, col, color);
    }
}
