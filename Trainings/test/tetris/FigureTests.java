package tetris;



import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FigureTests {

    @Test
    public void testFigureHeight()
    {
        Figure figure = new Figure(Figure.I);
        assertEquals(4,figure.figureHeight);

        Figure figure2 = new Figure(Figure.O);
        assertEquals(2,figure2.figureHeight);
    }

    @Test
    public void rotatedFigureHeight()
    {
        Figure I = new Figure(Figure.I);
        assertEquals(4,I.figureHeight);
        assertEquals(1,I.rotateFigure().figureHeight);

        Figure L = new Figure(Figure.L);
        assertEquals(3,L.figureHeight);
        assertEquals(2,L.rotateFigure().figureHeight);

        Figure S = new Figure(Figure.S);
        assertEquals(2, S.figureHeight);
        assertEquals(3,S.rotateFigure().figureHeight);
        assertEquals(2,S.rotateFigure().rotateFigure().figureHeight);
    }

}
