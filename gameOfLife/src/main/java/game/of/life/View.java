package game.of.life;

public class View {
	
	private PlatformGraphics graphics;
	
	public View() {
		graphics = (color, row, col) -> {};
	}
	
	public View(PlatformGraphics graphics) {
		this.graphics = graphics;
	}
}
