package GameOfLife.Tests;

import GameOfLife.MVC.Helpers.Logic;
import GameOfLife.MVC.Helpers.State;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiguresGenerationsTests {
    @Test
    public void aloneCellWithNoNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[15][15] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(1000);
        assertEquals(true,state.isStateEmpty());
    }

    @Test
    public void squareFigureWithSameGenerations() throws InterruptedException {
            State state = new State(30,30);
            state.getGameBoard()[15][15] = true;
            state.getGameBoard()[15][16] = true;
            state.getGameBoard()[16][16] = true;
            state.getGameBoard()[16][15] = true;
            Logic logic = new Logic(state);
            logic.SANITY_CHECK = true;
            logic.startProcessingCells();
            Thread.sleep(500);
            assertTrue(state.isCellAlive(15,15));
            assertTrue(state.isCellAlive(15,16));
            assertTrue(state.isCellAlive(16,16));
            assertTrue(state.isCellAlive(16,15));
    }

    @Test
    public void LFigureNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[15][15] = true;
        state.getGameBoard()[14][15] = true;
        state.getGameBoard()[13][16] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(500);
        assertTrue(state.isCellAlive(14,15));
        assertTrue(state.isCellAlive(14,16));
    }

    @Test
    public void LFigureNextNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[14][15] = true;
        state.getGameBoard()[14][16] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(500);
        assertEquals(true,state.isStateEmpty());
    }

    @Test
    public void cornerFigureNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[15][15] = true;
        state.getGameBoard()[16][15] = true;
        state.getGameBoard()[15][16] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(500);
        assertTrue(state.isCellAlive(15,15));
        assertTrue(state.isCellAlive(15,16));
        assertTrue(state.isCellAlive(16,16));
        assertTrue(state.isCellAlive(16,15));
    }

    @Test
    public void diagonalStickFigureNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[15][15] = true;
        state.getGameBoard()[14][14] = true;
        state.getGameBoard()[13][13] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(500);
        assertTrue(state.isCellAlive(14,14));
    }

    @Test
    public void horizontalStickFigureNextGeneration() throws InterruptedException {
        State state = new State(30,30);
        state.getGameBoard()[15][15] = true;
        state.getGameBoard()[16][15] = true;
        state.getGameBoard()[17][15] = true;
        state.getGameBoard()[18][15] = true;
        Logic logic = new Logic(state);
        logic.SANITY_CHECK = true;
        logic.startProcessingCells();
        Thread.sleep(500);
        assertTrue(state.isCellAlive(16,15));
        assertTrue(state.isCellAlive(16,16));
        assertTrue(state.isCellAlive(16,14));
        assertTrue(state.isCellAlive(17,15));
        assertTrue(state.isCellAlive(17,16));
        assertTrue(state.isCellAlive(17,14));
    }
}
