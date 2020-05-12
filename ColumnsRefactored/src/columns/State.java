package columns;

public class State {
	protected Field newField;
	protected Field oldField;
	protected Figure figure;
	protected int x;
	protected int y;
	
	public State() {
		newField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		oldField = new Field(Columns.WIDTH + Columns.LEFT_BORDER, Columns.HEIGHT + Columns.TOP_BORDER);
		
		launchNewFigure();
	}

	private void launchNewFigure() {
		figure = new Figure();
		x = Columns.WIDTH/2 + 1;
		y = 1;
	}
}
