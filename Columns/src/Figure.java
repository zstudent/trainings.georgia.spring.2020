import java.applet.*;
import java.awt.*;
import java.util.*;


class Figure {
	static private int x;
	static private int y;
	static private int[] c;

	public Figure() {
		c = new int[4];
		x = Columns.Width/2+1;
		y = 1;
		Random r = new Random();
		for (int i = 1; i < 4; i++){
			c[i] = Math.abs(r.nextInt())%7+1;
		}
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int val){
		x = val;
	}

	public void setY(int val){
		y = val;
	}

	public int getColumn(int index){
		return c[index];
	}

	public void setColumn(int index, int val){
		c[index] = val;
	}

	public void shiftDown(){
		int tmp = c[1];
		c[1] = c[3];
		c[3] = c[2];
		c[2] = tmp;
	}

	public void shiftUp(){
		int tmp = c[1];
		c[1] = c[2];
		c[2] = c[3];
		c[3] = tmp;
	}
}