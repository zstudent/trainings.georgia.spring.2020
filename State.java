
public class State {
	private Board board;
	private SnakeBody body;
	protected boolean gameOver;
	protected int score;
	protected int level;
	
	
	public State() {
		board = new Board(JSnake.HEIGHT, JSnake.WIDTH);
		SPoint initHead = new SPoint((JSnake.HEIGHT/2) - 1, (JSnake.WIDTH/2) - 1);
		SPoint initTail = new SPoint(initHead.row, initHead.col-1);
		SPoint []initBodyPoints = new SPoint[] {initHead, initTail};
		body = new SnakeBody(initBodyPoints);
		board.addSnakeBody(body);
		board.generateFruit();
		gameOver = false;
		score = 0;
		level = 1;
	}
	
	
	public Board getBoard() {
		return board;
	}
	
	
	public SnakeBody getBody() {
		return body;
	}
	
	
	public void moveSnake() {
		body.move();
		boolean eaten = eatFruit();
		board.undoSnakeBody();
		int checkGameOver = board.addSnakeBody(body);
		if(checkGameOver == Board.ADD_BAD && !eaten) {
			board.undoSnakeBody();
			board.addSnakeBody(body);
			gameOver = true;
		}
		if(checkGameOver == Board.ADD_BAD && eaten) {
			body.getBody().remove(body.getHead());
			body.getBody().add(body.generateNextTail());
			board.undoSnakeBody();
			board.addSnakeBody(body);
		}
	}
	
	
	public void turnLeft() {
		if(body.currDirrection != SnakeBody.RIGHT_DIR) {
			body.currDirrection = SnakeBody.LEFT_DIR;
		}
	}
	
	
	public void turnRight() {
		if(body.currDirrection != SnakeBody.LEFT_DIR) {
			body.currDirrection = SnakeBody.RIGHT_DIR;
		}
	}
	
	
	public void turnUp() {
		if(body.currDirrection != SnakeBody.DOWN_DIR) {
			body.currDirrection = SnakeBody.UP_DIR;
		}
	}
	
	
	public void turnDown() {
		if(body.currDirrection != SnakeBody.UP_DIR) {
			body.currDirrection = SnakeBody.DOWN_DIR;
		}
	}
	
	
	public boolean eatFruit() {
		if(board.getFruit().equals(body.getHead())) {
			board.grid[board.getFruit().row][board.getFruit().col] = false;
			body.grow();
			
			score++;
			if(score % 3 == 0 && score != 0) level+=1;
			board.generateFruit();
			return true;
		}
		return false;
	}
}
