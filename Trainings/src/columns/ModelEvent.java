package columns;

import java.util.Arrays;

public abstract class ModelEvent {
	
	static class FigureEvent extends ModelEvent {
		int x;
		int y;
		int[] c;

		public FigureEvent(Model model) {
			this.x = model.x;
			this.y = model.y;
			this.c = Arrays.copyOf(model.c, model.c.length);
		}
	}
	
	static class HideFigure extends FigureEvent {
		public HideFigure(Model model) {
			super(model);
		}
	}
	
	static class DrawFigure extends FigureEvent {
		public DrawFigure(Model model) {
			super(model);
		}
	}

}
