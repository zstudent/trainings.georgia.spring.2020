import java.applet.*;
import java.awt.*;


public class Columns extends Applet implements Runnable {
    static final int Depth=15;
    static final int Width=7;
    static final int MaxLevel=7;
    static final int TimeShift=250;
    static final int FigToDrop=33;
    static final int MinTimeShift=200;
    static final int LeftBorder=2;
    static final int TopBorder=2;
    
    
    Color[] colors = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
    Color.yellow,Color.pink,Color.magenta,Color.black};
    
    int level, k, ch;
    long score, DScore, timeCount;
    Figure figure;
    int[][] fNew;
    int[][] fOld;
    boolean noChanges = true;
    boolean keyPressed = false;
    Graphics graphics;
    Thread thread = null;
    
    
    void CheckNeighbours(int a, int b, int c, int d, int e, int f) {
        if ((fNew[f][e]== fNew[a][b]) && (fNew[f][e]== fNew[c][d])) {
            fOld[a][b] = 0;
            DrawBox(a,b,8);
            fOld[f][e] = 0;
            DrawBox(f,e,8);
            fOld[c][d] = 0;
            DrawBox(c,d,8);
            noChanges = false;
            score += (level +1)*10;
            k++;
        };
    }
    void Delay(long t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException ignored) {};
    }
    void DrawBox(int x, int y, int c) {
        int sl = 25;
        if (c==0) {
            graphics.setColor(Color.black);
            graphics.fillRect(LeftBorder+x*sl - sl, TopBorder+y*sl - sl, sl, sl);
            graphics.drawRect(LeftBorder+x*sl - sl, TopBorder+y*sl - sl, sl, sl);
        }
        else if (c==8) {
            graphics.setColor(Color.white);
            graphics.drawRect(LeftBorder+x*sl - sl +1, TopBorder+y* sl - sl +1, sl -2, sl -2);
            graphics.drawRect(LeftBorder+x*sl - sl +2, TopBorder+y* sl - sl +2, sl -4, sl -4);
            graphics.setColor(Color.black);
            graphics.fillRect(LeftBorder+x*sl - sl +3, TopBorder+y* sl - sl +3, sl -6, sl -6);
        }
        else {
            graphics.setColor(colors[c]);
            graphics.fillRect(LeftBorder+x*sl - sl, TopBorder+y* sl - sl, sl, sl);
            graphics.setColor(Color.black);
            graphics.drawRect(LeftBorder+x*sl - sl, TopBorder+y* sl - sl, sl, sl);
        }
        //		g.setColor (Color.black);
    }
    void DrawField(Graphics g) {
        int i,j;
        for (i=1; i<=Depth; i++) {
            for (j=1; j<=Width; j++) {
                DrawBox(j, i, fNew[j][i]);
            }
        }
    }
    void DrawFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            DrawBox(f.getX(),f.getY()+i,f.getColumn(i+1));
        }
    }
    void DropFigure(Figure f) {
        if (f.getY() < Depth-2) {
            int d = Depth;
            while (fNew[f.getX()][d]>0) d--;
            DScore = (((level +1)*(Depth*2-f.getY()-d) * 2) % 5) * 5;
            f.setY(d-2);
        }
    }
    boolean FullField() {
        int i;
        for (i=1; i<=Width; i++) {
            if (fNew[i][3]>0)
                return true;
        }
        return false;
    }
    void HideFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            DrawBox(f.getX(),f.getY()+i,0);
        }
    }
    public void init() {
        fNew = new int[Width+2][Depth+2];
        fOld = new int[Width+2][Depth+2];
        graphics = getGraphics();
    }
    public boolean keyDown(Event e, int k) {
        keyPressed = true;
        ch = e.key;
        return true;
    }
    public boolean lostFocus(Event e, Object w) {
        keyPressed = true;
        ch = 'P';
        return true;
    }
    void PackField() {
        int i,j,n;
        for (i=1; i<=Width; i++) {
            n = Depth;
            for (j=Depth; j>0; j--) {
                if (fOld[i][j]>0) {
                    fNew[i][n] = fOld[i][j];
                    n--;
                }
            };
            for (j=n; j>0; j--) fNew[i][j] = 0;
        }
    }
    public void paint(Graphics g) {
        //		ShowHelp(g);
        g.setColor(Color.black);
        ShowLevel(g);
        ShowScore(g);
        DrawField(g);
        DrawFigure(figure);
        requestFocus();
    }
    void PasteFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            fNew[f.getX()][f.getY()+i] = f.getColumn(i+1);
        }
    }
    public void run() {
        for (int i=0; i<Width+1; i++){
            for (int j=0; j<Depth+1; j++) {
                fNew[i][j] = 0;
                fOld[i][j] = 0;
            }
        }
        level = 0;
        score = 0;
        k = 0;
        graphics.setColor(Color.black);
        requestFocus();
        
        do {
            timeCount = System.currentTimeMillis();
            new Figure();
            DrawFigure(figure);
            while ((figure.getY()<Depth-2) && (fNew[figure.getX()][figure.getY()+3]==0)) {
                if ((int)(System.currentTimeMillis()- timeCount)>(MaxLevel- level)*TimeShift+MinTimeShift) {
                    timeCount = System.currentTimeMillis();
                    HideFigure(figure);
                    figure.setY(figure.getY()+1);
                    DrawFigure(figure);
                }
                DScore = 0;
                do {
                    Delay(50);
                    if (keyPressed) {
                        keyPressed = false;
                        switch (ch) {
                            case Event.LEFT:
                                if ((figure.getX()>1) && (fNew[figure.getX()-1][figure.getY()+2]==0)) {
                                    HideFigure(figure);
                                    figure.setX(figure.getX()-1);
                                    DrawFigure(figure);
                                }
                                break;
                            case Event.RIGHT:
                                if ((figure.getX()<Width) && (fNew[figure.getX()+1][figure.getY()+2]==0)) {
                                    HideFigure(figure);
                                    figure.setX(figure.getX()+1);
                                    DrawFigure(figure);
                                }
                                break;
                            case Event.UP:
                                for(int i = 1 ; i < 4; i++){
                                    figure.setColumn(1, figure.getColumn(i%3));
                                }
                                DrawFigure(figure);
                                break;
                            case Event.DOWN:
                                figure.setColumn(1, figure.getColumn(3));
                                figure.setColumn(3, figure.getColumn(2));
                                figure.setColumn(2, figure.getColumn(1));
                                figure.setColumn(figure.getColumn(1), (figure.getColumn(1)%3+2));
                                DrawFigure(figure);
                                break;
                            case ' ':
                                HideFigure(figure);
                                DropFigure(figure);
                                DrawFigure(figure);
                                timeCount = 0;
                                break;
                            case 'P':
                            case 'p':
                                while (!keyPressed) {
                                    HideFigure(figure);
                                    Delay(500);
                                    DrawFigure(figure);
                                    Delay(500);
                                }
                                timeCount = System.currentTimeMillis();
                                break;
                            case '-':
                                if (level > 0) level--;
                                k=0;
                                ShowLevel(graphics);
                                break;
                            case '+':
                                if (level < MaxLevel) level++;
                                k=0;
                                ShowLevel(graphics);
                                break;
                        }
                    }
                } while ( (int)(System.currentTimeMillis()- timeCount) <= (MaxLevel- level)*TimeShift+MinTimeShift );
            };
            PasteFigure(figure);
            do {
                noChanges = true;
                TestField();
                if (!noChanges) {
                    Delay(500);
                    PackField();
                    DrawField(graphics);
                    score += DScore;
                    ShowScore(graphics);
                    if (k>=FigToDrop) {
                        k = 0;
                        if (level <MaxLevel) level++;
                        ShowLevel(graphics);
                    }
                }
            } while (!noChanges);
        }while (!FullField());
    }
    void ShowHelp(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Keys available:",200-LeftBorder,102);
        g.drawString("Roll Box Up:     ",200-LeftBorder,118);
        g.drawString("Roll Box Down:   ",200-LeftBorder,128);
        g.drawString("Figure Left:     ",200-LeftBorder,138);
        g.drawString("Figure Right:    ",200-LeftBorder,148);
        g.drawString("Level High/Low: +/-",200-LeftBorder,158);
        g.drawString("Drop Figure:   space",200-LeftBorder,168);
        g.drawString("Pause:           P",200-LeftBorder,180);
        g.drawString("Quit:     Esc or Q",200-LeftBorder,190);
    }
    void ShowLevel(Graphics g) {
        g.setColor(Color.black);
        g.clearRect(LeftBorder+100,390,100,20);
        g.drawString("Level: "+ level,LeftBorder+100,400);
    }
    void ShowScore(Graphics g) {
        g.setColor(Color.black);
        g.clearRect(LeftBorder,390,100,20);
        g.drawString("Score: "+ score,LeftBorder,400);
    }
    public void start() {
        graphics.setColor(Color.black);
        //		setBackground (new Color(180,180,180));
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }
    void TestField() {

        for (int i=1; i<=Depth; i++) {
            for (int j=1; j<=Width; j++) {
                fOld[j][i] = fNew[j][i];
            }
        }
        for (int i=1; i<=Depth; i++) {
            for (int j=1; j<=Width; j++) {
                if (fNew[j][i]>0) {
                    CheckNeighbours(j,i-1,j,i+1,i,j);
                    CheckNeighbours(j-1,i,j+1,i,i,j);
                    CheckNeighbours(j-1,i-1,j+1,i+1,i,j);
                    CheckNeighbours(j+1,i-1,j-1,i+1,i,j);
                }
            }
        }
    }
}