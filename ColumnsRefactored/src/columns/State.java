package columns;

public class State {
	protected Field newField;
	protected Field oldField;
	protected Figure figure;
	protected Paint paint;
	protected int x;
	protected int y;
	
	public State(Paint paint) {
		newField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		oldField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		this.paint = paint;
		
		launchNewFigure();
	}

	private void launchNewFigure() {
		figure = new Figure();
		x = Columns.WIDTH/2 + 1;
		y = 1;
	}
}
