package tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FigureFitsFieldTests {

	private Logic logic;

	@Before
	public void setUp() {
		logic = new Logic(new State());
	}

	@Test
	public void testFourHorizontalFitsTheField() throws Exception {
		logic.state.col = 0;
		logic.state.figure = new Figure(Figure.I);
		assertTrue(logic.state.isFigureFitTheField());
	}

	@Test
	public void testFourHorizontalDoesNotFitTheField() throws Exception {
		logic.state.col = -1;
		logic.state.figure = new Figure(Figure.I);
		assertFalse(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testFourHorizontalFitsTheFieldAtRight() throws Exception {
		int width = logic.state.field.data[0].length;
		int figureWidth = logic.state.figure.data[0].length;
		logic.state.col = width - figureWidth;
		logic.state.figure = new Figure(Figure.I);
		assertTrue(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testFourHorizontalDoesnNotFitTheFieldAtRight() throws Exception {
		int width = logic.state.field.data[0].length;
		int figureWidth = logic.state.figure.data[0].length;
		logic.state.col = width - figureWidth + 1;
		logic.state.figure = new Figure(Figure.I);
		assertFalse(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testTripleUpFitsTheField() throws Exception {
		logic.state.col = 0;
		logic.state.figure = new Figure(Figure.T);
		assertTrue(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testTripleUpFitsTheFieldInTheLowerPart() throws Exception {
		int height = logic.state.field.data.length;
		logic.state.row = height - 4;
		logic.state.figure = new Figure(Figure.T);
		assertTrue(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testTripleUpDOesNotFitTheField() throws Exception {
		logic.state.col = -2;
		logic.state.figure = new Figure(Figure.T);
		assertFalse(logic.state.isFigureFitTheField());
	}
	
	@Test
	public void testFigureOverlapsBoxesOnTheField() throws Exception {
		logic.state.figure = new Figure(Figure.T);
		logic.state.field.data[logic.state.row][logic.state.col + 1] = 1;
		assertFalse(logic.state.isFigureFitTheField());
	}
	
	
}
