
import java.applet.*;
import java.awt.*;
import java.util.*;


class Figure {
	static public int x;
	static public int y;
	static public int[] c;
	static public Random r = new Random();

	Figure() {
		r = new Random();
		c = new int[4];
		x = Columns.Width/2+1;
		y = 1;
		for(int i = 1; i < 4; i++){
			c[i] = Math.abs(r.nextInt())%7+1;
		}
	}

	public int getY(){
		return y;
	}

	public void setY(int val){
		y = val;
	}

	public int getX(){
		return x;
	}

	public void setX(int val){
		x = val;
	}

	public int getColumn(int index){
		return c[index];
	}

	public void setColumn(int index, int val){
		c[index] = val;
	}
}