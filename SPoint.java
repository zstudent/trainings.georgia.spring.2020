/*
 This is just a trivial "struct" type class --
 it simply holds an int row/col point for use by Snake,
 and supports equals().
 */

public class SPoint {
	public int row;
	public int col;
	
	public SPoint(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	// Creates a SPoint, copied from an existing SPoint
	public SPoint(SPoint point) {
		this.row = point.row;
		this.col = point.col;
	}
	
	
	// Standard equals() override
	@Override
	public boolean equals(Object other) {
		// standard two checks for equals()
		if (this == other) return true;
		if (!(other instanceof SPoint)) return false;

		// check if other point same as us
		SPoint pt = (SPoint)other;
		return(row==pt.row && col==pt.col);
	}
}
