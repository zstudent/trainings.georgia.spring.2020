package columns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class View {

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
	
	 Color colorsList[] = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
			    Color.yellow,Color.pink,Color.magenta,Color.black};
	
	
	Graphics graphic;
	
	public View(Graphics graphic) {
		this.graphic = graphic;
	}
	
	
	
	void DrawBox(int x, int y, int c) {
    	//Field
        if (c==0) {
            graphic.setColor(Color.black);
            graphic.fillRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
            graphic.drawRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
        }
        //White borders while cleaning
        else if (c==8) {
            graphic.setColor(Color.white);
            graphic.drawRect(LeftBorder+x*SL-SL+1, TopBorder+y*SL-SL+1, SL-2, SL-2);
            graphic.drawRect(LeftBorder+x*SL-SL+2, TopBorder+y*SL-SL+2, SL-4, SL-4);
            graphic.setColor(Color.black);
            graphic.fillRect(LeftBorder+x*SL-SL+3, TopBorder+y*SL-SL+3, SL-6, SL-6);
        }
        //Figure
        else {
            graphic.setColor(colorsList[c]);
            graphic.fillRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
            graphic.setColor(Color.black);
            graphic.drawRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
        }
        //		g.setColor (Color.black);
    }
	public void afterCleaningNeighboursDraw(int i, int j, int c) {
		DrawBox(i, j, c);
	}
	
    public void DrawField(int[][] newField) {
    	int i,j;
        for (i=1; i<=HEIGHT; i++) {
            for (j=1; j<=WIDTH; j++) {
                DrawBox(j,i,newField[j][i]);
            }
        }
    }
    
    
    public void DrawFigure(Figure f) {

    	for (int i = 0; i < 3; i++) {
			DrawBox(f.x, f.y+i, f.c[i == 0 ? 1 : i + 1]);
		}
    }
    
    public void HideFigure(Figure f) {
        DrawBox(f.x,f.y,0);
        DrawBox(f.x,f.y+1,0);
        DrawBox(f.x,f.y+2,0);
    }
    
    void ShowLevel(int level) {
        graphic.setColor(Color.black);
        graphic.clearRect(LeftBorder+100,390,100,20);
        graphic.drawString("Level: "+level,LeftBorder+100,400);
    }
    void ShowScore(long score) {
        graphic.setColor(Color.black);
        graphic.clearRect(LeftBorder,390,100,20);
        graphic.drawString("Score: "+score,LeftBorder,400);
    }
	
	
}
