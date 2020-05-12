package columns;

public class State {
	
	static final int
    SL=25,
    HEIGHT=15,
    WIDTH=7,
    MaxLevel=7,
    TimeShift=250,
    FigToDrop=33,
    MinTimeShift=200,
    LeftBorder=2,
    TopBorder=2;
	
	int Level, i, j, ii, k, ch;
    long Score, DScore, tc;
    boolean NoChanges = true, paused = false;
	
	Figure fig;
	int[][] newField,oldField;
	
	public State() {
		newField = new int[WIDTH+2][HEIGHT+2];
		oldField = new int[WIDTH+2][HEIGHT+2];
		this.fig = new Figure();
	}

}
