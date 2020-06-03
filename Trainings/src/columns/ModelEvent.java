package columns;

import java.util.Arrays;

public abstract class ModelEvent {
	
	static abstract class FigureEvent extends ModelEvent {
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

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
	}
	
	static class DrawFigure extends FigureEvent {
		public DrawFigure(Model model) {
			super(model);
		}

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
	}
	
	static class Delay extends ModelEvent {
		int millis;

		public Delay(int millis) {
			this.millis = millis;
		}

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
	}
	
	static class ShowScore extends ModelEvent {
		long score;
		
		public ShowScore(long score) {
			this.score = score;
		}

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
	}
	
	static class ShowLevel extends ModelEvent {
		int level;
		
		public ShowLevel(int level) {
			this.level = level;
		}

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
	}
	
	static class DrawField extends ModelEvent {
		int[][] field;

		public DrawField(int[][] field) {
			this.field = new int[field.length][];
			for (int i = 0; i < field.length; i++) {
				this.field[i] = Arrays.copyOf(field[i], field[i].length);
			}
		}

		@Override
		protected void accept(View view) {
			view.visit(this);
		}
		
	}

	protected abstract void accept(View view);

}
