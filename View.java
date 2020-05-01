
public class View {
	
	private PlatfromGraphics graphics;
	
	public View(PlatfromGraphics graphics) {
		this.graphics = graphics;
	}
	
	public void draw(State state) {
		int[][] data = state.field.data;
		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				int color = data[r][c];
				graphics.fillRectangle(color, r, c);
			}
		}
	}
}
