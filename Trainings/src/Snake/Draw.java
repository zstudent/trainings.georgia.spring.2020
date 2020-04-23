package Snake;

import java.awt.*;

@FunctionalInterface
public interface Draw {
    void drawRect(Color color, int row, int col, int width, int height);
}
