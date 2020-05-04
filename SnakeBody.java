import java.util.ArrayList;
import java.util.List;

public class SnakeBody {
	private List<SPoint> body;
	public String currDirrection;
	
	static final String LEFT_DIR = "Left";
	static final String RIGHT_DIR = "Right";
	static final String UP_DIR = "Up";
	static final String DOWN_DIR = "Down";
	
	public SnakeBody(SPoint[] body) {
		currDirrection = RIGHT_DIR;
		this.body = new ArrayList<SPoint>();
		this.body.add(body[0]);
		for(int i=1; i<body.length; i++) {
			this.body.add(body[i]);
		}
	}
	
	public List<SPoint> getBody(){
		return body;
	}
	
	public SPoint getHead() {
		return body.get(0);
	}
	
	public SPoint getTail() {
		return body.get(body.size()-1);
	}
	
	public void grow() {
		body.add(0, generateNextHead());
	}
	
	public void move() {
		grow();
		body.remove(body.size()-1);
	}
	
	
	public SPoint generateNextTail() {
		int row = getTail().row;
		int col = getTail().col;
		
		switch(currDirrection) {
			case RIGHT_DIR:
				col--;
				break;
			case LEFT_DIR:
				col++;
				break;
			case UP_DIR:
				row++;
				break;
			case DOWN_DIR:
				row--;
				break;
		}
		
		return new SPoint(row,col);
		
	}
	
	
	private SPoint generateNextHead() {
		int row = getHead().row;
		int col = getHead().col;
		
		switch(currDirrection) {
			case RIGHT_DIR:
				col++;
				break;
			case LEFT_DIR:
				col--;
				break;
			case UP_DIR:
				row--;
				break;
			case DOWN_DIR:
				row++;
				break;
		}
		
		return new SPoint(row,col);
		
	}
}