package columns;
import java.applet.*;
import java.awt.*;
import java.util.*;


public class Columns extends Applet implements Runnable {
    static final int
    columnRectSize=35,
    Depth=14,
    Width=10,
    MaxLevel=7,
    TimeShift=250,
    DeletedFiguresToNextLevel=10,
    MinTimeShift=200,
    LeftPadding=2,
    TopPadding=2,
    HelpStringsGap = 15;
    
    Color MyStyles[] = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
    Color.yellow,Color.pink,Color.magenta,Color.black};
    int Level, countDeletedFigures, KeyCode;
    long Score, DScore, tc;
    Font fCourier;
    Figure Fig;
    int Fnew[][],Fold[][];
    boolean MatchFigureColor = false, KeyPressed = false;
    Graphics graphics;
    Thread thr = null;

    void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
        if ((Fnew[j][i]==Fnew[a][b]) && (Fnew[j][i]==Fnew[c][d])) {
            Fold[a][b] = 0;
            Fold[j][i] = 0;
            Fold[c][d] = 0;
            DrawBox(j,i,8);
            DrawBox(a,b,8);
            DrawBox(c,d,8);
            MatchFigureColor = true;
            Score += (Level+1)*10;
            countDeletedFigures++;
        };
    }
    void Delay(long t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException e) {};
    }
    void DrawBox(int x, int y, int style) {
        if (style==0) {
            graphics.setColor(Color.black);
            graphics.fillRect(LeftPadding+x*columnRectSize-columnRectSize, TopPadding+y*columnRectSize-columnRectSize, columnRectSize, columnRectSize);
            graphics.drawRect(LeftPadding+x*columnRectSize-columnRectSize, TopPadding+y*columnRectSize-columnRectSize, columnRectSize, columnRectSize);
        }
        else if (style==8) {
            graphics.setColor(Color.white);
            graphics.drawRect(LeftPadding+x*columnRectSize-columnRectSize+1, TopPadding+y*columnRectSize-columnRectSize+1, columnRectSize-2, columnRectSize-2);
            graphics.drawRect(LeftPadding+x*columnRectSize-columnRectSize+2, TopPadding+y*columnRectSize-columnRectSize+2, columnRectSize-4, columnRectSize-4);
            graphics.setColor(Color.black);
            graphics.fillRect(LeftPadding+x*columnRectSize-columnRectSize+3, TopPadding+y*columnRectSize-columnRectSize+3, columnRectSize-6, columnRectSize-6);
        }
        else {
            graphics.setColor(MyStyles[style]);
            graphics.fillRect(LeftPadding+x*columnRectSize-columnRectSize, TopPadding+y*columnRectSize-columnRectSize, columnRectSize, columnRectSize);
            graphics.setColor(Color.black);
            graphics.drawRect(LeftPadding+x*columnRectSize-columnRectSize, TopPadding+y*columnRectSize-columnRectSize, columnRectSize, columnRectSize);
        }
    }
    void DrawField() {
        int i,j;
        for (i=1; i<=Depth; i++) {
            for (j=1; j<=Width; j++) {
                DrawBox(j,i,Fnew[j][i]);
            }
        }
    }
    void DrawFigure(Figure f) {
        DrawBox(f.x,f.y,f.c[1]);
        DrawBox(f.x,f.y+1,f.c[2]);
        DrawBox(f.x,f.y+2,f.c[3]);
    }
    void DropFigure(Figure f) {
        int zz;
        if (f.y < Depth-2) {
            zz = Depth;
            while (Fnew[f.x][zz]>0) zz--;
            DScore = (((Level+1)*(Depth*2-f.y-zz) * 2) % 5) * 5;
            f.y = zz-2;
        }
    }
    boolean gameOver() {
        int i;
        for (i=1; i<=Width; i++) {
            if (Fnew[i][3]>0)
                return true;
        }
        return false;
    }
    void HideFigure(Figure f) {
        DrawBox(f.x,f.y,0);
        DrawBox(f.x,f.y+1,0);
        DrawBox(f.x,f.y+2,0);
    }
    public void init() {
    	Level = 0;
        Fnew = new int[Width+2][Depth+2];
        Fold = new int[Width+2][Depth+2];
        graphics = getGraphics();
        setSize(Width*columnRectSize+LeftPadding,Depth*columnRectSize+TopPadding+9*HelpStringsGap);
    }
    public boolean keyDown(Event e, int k) {
        KeyPressed = true;
        KeyCode = e.key;
        return true;
    }
    void removeIdenticalRecs() {
        int i,j,n;
        for (i=1; i<=Width; i++) {
            n = Depth;
            for (j=Depth; j>0; j--) {
                if (Fold[i][j]>0) {
                    Fnew[i][n] = Fold[i][j];
                    n--;
                }
            };
            for (j=n; j>0; j--) Fnew[i][j] = 0;
        }
    }
    public void paint(Graphics g) {
        ShowHelp(g);
        ShowLevel(g);
        ShowScore(g);
        DrawField();
        DrawFigure(Fig);
        requestFocus();
    }
    void PasteFigure(Figure f) {
        Fnew[f.x][f.y] = f.c[1];
        Fnew[f.x][f.y+1] = f.c[2];
        Fnew[f.x][f.y+2] = f.c[3];
    }
    public void run() {
        fillFiguresWithZeros();
        Level = countDeletedFigures = 0;
        Score = 0;
        requestFocus();
        while(!gameOver()) {
            new Figure();
            DrawFigure(Fig);
            while ((Fig.y<Depth-2) && (Fnew[Fig.x][Fig.y+3]==0)) {
            	tc = System.currentTimeMillis();
                HideFigure(Fig);
                Fig.y++;
                DrawFigure(Fig);
                DScore = 0;
                while ( (int)(System.currentTimeMillis()-tc) <= (MaxLevel-Level)*TimeShift+MinTimeShift ) {
                    if (KeyPressed) getKeyAndPerform();
                }
            }
            plcaeFigureAndCheckSimilarity();
        }
        stop();
    }
	private void getKeyAndPerform() {
		KeyPressed = false;
		int firstTempRect;
		switch (KeyCode) {
		    case Event.LEFT:
		        if ((Fig.x>1) && (Fnew[Fig.x-1][Fig.y+2]==0)) {
		            HideFigure(Fig);
		            Fig.x--;
		            DrawFigure(Fig);
		        }
		        break;
		    case Event.RIGHT:
		        if ((Fig.x<Width) && (Fnew[Fig.x+1][Fig.y+2]==0)) {
		            HideFigure(Fig);
		            Fig.x++;
		            DrawFigure(Fig);
		        }
		        break;
		    case Event.UP:
		        firstTempRect = Fig.c[1];
		        Fig.c[1] = Fig.c[2];
		        Fig.c[2] = Fig.c[3];
		        Fig.c[3] = firstTempRect;
		        DrawFigure(Fig);
		        break;
		    case Event.DOWN:
		        firstTempRect = Fig.c[1];
		        Fig.c[1] = Fig.c[3];
		        Fig.c[3] = Fig.c[2];
		        Fig.c[2] = firstTempRect;
		        DrawFigure(Fig);
		        break;
		    case ' ':
		        HideFigure(Fig);
		        DropFigure(Fig);
		        DrawFigure(Fig);
		        break;
		    case 'p':
		        while (!KeyPressed) {
		            HideFigure(Fig);
		            Delay(500);
		            DrawFigure(Fig);
		            Delay(500);
		        }
		        tc = System.currentTimeMillis();
		        break;
		    case '-':
		        if (Level > 0) Level--;
		        countDeletedFigures=0;
		        ShowLevel(graphics);
		        break;
		    case '+':
		        if (Level < MaxLevel) Level++;
		        countDeletedFigures=0;
		        ShowLevel(graphics);
		        break;
		}
	}
	private void fillFiguresWithZeros() {
		for (int i=0; i<Width+1; i++){
            for (int j=0; j<Depth+1; j++) {
                Fnew[i][j] = 0;
                Fold[i][j] = 0;
            }
        }
	}
	private void plcaeFigureAndCheckSimilarity() {
		PasteFigure(Fig);
		do {
		    MatchFigureColor = false;
		    TestField();
		    if (MatchFigureColor) {
		        Delay(500);
		        removeIdenticalRecs();
		        DrawField();
		        Score += DScore;
		        ShowScore(graphics);
		        if (countDeletedFigures>=DeletedFiguresToNextLevel) {
		            countDeletedFigures = 0;
		            if (Level<MaxLevel) Level++;
		            ShowLevel(graphics);
		        }
		    }
		} while (MatchFigureColor);
	}
    void ShowHelp(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Keys available:",200-LeftPadding,Depth*columnRectSize+TopPadding+HelpStringsGap);
        g.drawString(" Roll Box Up:",200-LeftPadding,Depth*columnRectSize+TopPadding+2*HelpStringsGap);
        g.drawString(" Roll Box Down:",200-LeftPadding,Depth*columnRectSize+TopPadding+3*HelpStringsGap);
        g.drawString(" Figure Left:",200-LeftPadding,Depth*columnRectSize+TopPadding+4*HelpStringsGap);
        g.drawString(" Figure Right:",200-LeftPadding,Depth*columnRectSize+TopPadding+5*HelpStringsGap);
        g.drawString(" Level High/Low: +/-",200-LeftPadding,Depth*columnRectSize+TopPadding+6*HelpStringsGap);
        g.drawString(" Drop Figure:   space",200-LeftPadding,Depth*columnRectSize+TopPadding+7*HelpStringsGap);
        g.drawString(" Pause:           P",200-LeftPadding,Depth*columnRectSize+TopPadding+8*HelpStringsGap);
        g.drawString(" Quit:     Esc or Q",200-LeftPadding,Depth*columnRectSize+TopPadding+9*HelpStringsGap);
    }
    void ShowLevel(Graphics g) {
        g.setColor(Color.black);
        g.clearRect(LeftPadding+100,(Depth+1)*columnRectSize+TopPadding-10,100,20);
        g.drawString("Level: "+Level,LeftPadding+100,(Depth+1)*columnRectSize+TopPadding);
        
    }
    void ShowScore(Graphics g) {
        g.setColor(Color.black);
        g.clearRect(LeftPadding,(Depth+1)*columnRectSize+TopPadding-10,100,20);
        g.drawString("Score: "+Score,LeftPadding,(Depth+1)*columnRectSize+TopPadding);
    }
    public void start() {
        graphics.setColor(Color.black);
        if (thr == null) {
            thr = new Thread(this);
            thr.start();
        }
    }
    public void stop() {
        if (thr != null) {
            thr.stop();
            thr = null;
        }
    }
    void TestField() {
        int i,j;
        for (i=0; i<=Depth; i++) {
            for (j=0; j<=Width; j++) {
                Fold[j][i] = Fnew [j][i];
            }
        }
        for (i=1; i<=Depth; i++) {
            for (j=1; j<=Width; j++) {
                if (Fnew[j][i]>0) {
                    CheckNeighbours(j,i-1,j,i+1,i,j);
                    CheckNeighbours(j-1,i,j+1,i,i,j);
                    CheckNeighbours(j-1,i-1,j+1,i+1,i,j);
                    CheckNeighbours(j+1,i-1,j-1,i+1,i,j);
                }
            }
        }
    }
}