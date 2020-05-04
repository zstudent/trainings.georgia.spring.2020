import java.util.Arrays;
import java.util.List;
import java.util.Random;

//* Represents a Snake board -- essentially a 2-d grid of booleans.
public class Board {
	protected int width;
	protected int height;
	protected boolean[][] grid;
	private boolean[][] xGrid;
	private SPoint fruit;
	
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int row, int col) {
		this.height = row;
		this.width = col;
		grid = new boolean[row][col];
		xGrid = new boolean[row][col];
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns true if the given block is filled currently in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int row, int col) {
		// my code
		if(row >= height || col >= width || row < 0 || col < 0) return true;
		return grid[row][col];
	}
	
	
	public static final int ADD_OK = 0;
	public static final int ADD_BAD  = 1;
	
	
	/**
	  Attempts to add the body of a snake to the board.
	  Returns ADD_OK for a regular adding(inside row/col and not crossing head of snake to it's other parts)
	  Returns ADD_Bad for not regular adding(it means that game is over)
	 */
	public int addSnakeBody(SnakeBody snakeBody) {
		int result = ADD_OK;
		
		List<SPoint> body = snakeBody.getBody();
		SPoint head = snakeBody.getHead();
		if(head.row >= height || head.col >= width || head.row  <0 || head.col < 0) return ADD_BAD;
		
		backupSnakeBody();

		grid[head.row][head.col] = true;
		
		for(int i=1; i<body.size(); i++) {
			SPoint currPoint = body.get(i);
			if(currPoint.equals(head)) result = ADD_BAD;
			grid[currPoint.row][currPoint.col] = true;
		}
		
		return result;
	}
	
	
	public void backupSnakeBody() {
		for(int i=0; i<grid.length; i++) {
			Arrays.fill(xGrid[i], false);
		}
		if(fruit != null) xGrid[fruit.row][fruit.col] = true;
	}
	
	
	public void undoSnakeBody() {
		boolean temp[][] = grid;
		grid = xGrid;
		xGrid = temp;
		grid[fruit.row][fruit.col] = true;
	}
	
	
	public void generateFruit() {
		Random rand = new Random();
		boolean check = false;
		int row = 0;
		int col = 0;
		while(!check) {
			row = rand.nextInt(height);
			col = rand.nextInt(width);
			check = !getGrid(row, col);
		}
		grid[row][col] = true;
		fruit = new SPoint(row, col);
	}
	
	
	public SPoint getFruit() {
		return fruit;
	}
	
	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (debugging utility) 
	 */
	@Override
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int row = 0; row<height; row++) {
			buff.append('|');
			for (int y=0; y<width; y++) {
				if (getGrid(row,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
	
	
}
