import java.applet.*;
import java.awt.*;


public class Columns extends Applet implements Runnable {

    //static final int SL=25;
   // static final int Depth=15;
    //static final int Width=7;
    //static final int MaxLevel=7;
    static final int TimeShift=250;
    //static final int FigToDrop=33;
    static final int MinTimeShift=200;
   //static final int LeftBorder=2;
    //static final int TopBorder=2;
    
    /*
    Color[] MyStyles = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
    Color.yellow,Color.pink,Color.magenta,Color.black};
    
     */
    
    //int level, k;
    int ch;
    //long score;
    //long dScore;
    long timeCount;
    //Figure figure;
    //int[][] fNew;
    //int[][] fOld;
    //boolean noChanges;
    boolean keyPressed;
    boolean gameOn;
    Graphics graphics;
    Painter painter;
    State state;
    Thread thread;

    /*
    void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
        if ((fNew[j][i]== fNew[a][b]) && (fNew[j][i]== fNew[c][d])) {
            fOld[a][b] = 0;
            painter.drawBox(a,b,8);
            fOld[j][i] = 0;
            painter.drawBox(j,i,8);
            fOld[c][d] = 0;
            painter.drawBox(c,d,8);
            noChanges = false;
            score += (level +1)*10;
            k++;
        };
    }

     */
    public static void delay(long t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException ignored) {};
    }
    /*
    void DrawBox(int x, int y, int c) {
        if (c==0) {
            graphics.setColor(Color.black);
            graphics.fillRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
            graphics.drawRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
        }
        else if (c==8) {
            graphics.setColor(Color.white);
            graphics.drawRect(LeftBorder+x*SL-SL+1, TopBorder+y*SL-SL+1, SL-2, SL-2);
            graphics.drawRect(LeftBorder+x*SL-SL+2, TopBorder+y*SL-SL+2, SL-4, SL-4);
            graphics.setColor(Color.black);
            graphics.fillRect(LeftBorder+x*SL-SL+3, TopBorder+y*SL-SL+3, SL-6, SL-6);
        }
        else {
            graphics.setColor(MyStyles[c]);
            graphics.fillRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
            graphics.setColor(Color.black);
            graphics.drawRect(LeftBorder+x*SL-SL, TopBorder+y*SL-SL, SL, SL);
        }
        //		g.setColor (Color.black);
    }
    void DrawField(Graphics g) {
        for (int i=1; i<=Depth; i++) {
            for (int j=1; j<=Width; j++) {
                DrawBox(j,i, fNew[j][i]);
            }
        }
    }
    void DrawFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            DrawBox(f.getX(),f.getY()+i,f.getColumn(i+1));
        }

        DrawBox(f.x,f.y,f.c[1]);
        DrawBox(f.x,f.y+1,f.c[2]);
        DrawBox(f.x,f.y+2,f.c[3]);

    }

    */

    /*
    void DropFigure(Figure f) {
        if (f.getY() < Depth-2) {
            int tmp = Depth;
            while (fNew[f.getX()][tmp]>0) tmp--;
            dScore = (((level +1)*(Depth*2-f.getY()-tmp) * 2) % 5) * 5;
            f.setY(tmp-2);
        }
        /*
        int zz;
        if (f.y < Depth-2) {
            zz = Depth;
            while (Fnew[f.x][zz]>0) zz--;
            DScore = (((Level+1)*(Depth*2-f.y-zz) * 2) % 5) * 5;
            f.y = zz-2;
        }

    }
    */

    /*
    boolean FullField() {
        int i;
        for (i=1; i<=Width; i++) {
            if (fNew[i][3]>0)
                return true;
        }
        return false;
    }

     */
    /*
    void HideFigure(Figure f) {
        for(int i = 0; i<3;i++){
            DrawBox(f.getX(), f.getY()+i,0);
        }

        DrawBox(f.x,f.y,0);
        DrawBox(f.x,f.y+1,0);
        DrawBox(f.x,f.y+2,0);

    }
    */

    @Override
    public void init() {
        graphics = getGraphics();
        //figure = new Figure();
        //noChanges = true;
        keyPressed = false;
        gameOn = true;
        /*
        fNew = new int[Width+2][Depth+2];
        fOld = new int[Width+2][Depth+2];
        level = 0;
        score = 0;
        k = 0;
         */
        painter = new Painter(graphics);
        state = new State(painter);
        requestFocus();
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
    /*
    void PackField() {
        int n;
        for (int i=1; i<=Width; i++) {
            n = Depth;
            for (int j=Depth; j>0; j--) {
                if (fOld[i][j]>0) {
                    fNew[i][n] = fOld[i][j];
                    n--;
                }
            };
            for (int j = n; j>0; j--) fNew[i][j] = 0;
        }
    }
    */


    @Override
    public void paint(Graphics g) {
        //		ShowHelp(g);
        /*
        g.setColor(Color.black);
        painter.ShowLevel(Level);
        painter.ShowScore(score);
        painter.DrawField(fNew);
        painter.DrawFigure(figure);
         */
        state.initialPaint();
        requestFocus();
    }
  /*
    void PasteFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            fNew[f.getX()][f.getY()+i] = f.getColumn(i+1);
        }

        Fnew[f.x][f.y] = f.c[1];
        Fnew[f.x][f.y+1] = f.c[2];
        Fnew[f.x][f.y+2] = f.c[3];

    }
    */

    public void run() {
        /*
        for (int i=0; i<Width+1; i++){
            for (int j=0; j<Depth+1; j++) {
                Fnew[i][j] = 0;
                Fold[i][j] = 0;
            }
        }

         */
        
        do {
            timeCount = System.currentTimeMillis();
            state.generateFigure();
            painter.drawFigure(state.getFigure());
            while ((state.getFigure().getY()<State.DEPTH-2) && (state.getFieldValue()==0)) {
                if ((int)(System.currentTimeMillis()- timeCount)>(State.MaxLevel- state.getLevel())*TimeShift+MinTimeShift) {
                    timeCount = System.currentTimeMillis();
                    painter.hideFigure(state.getFigure());
                    state.getFigure().setY(state.getFigure().getY()+1);
                    painter.drawFigure(state.getFigure());
                }
                state.setDsToZero();
                do {
                    delay(50);
                    if (keyPressed) {
                        keyPressed = false;
                        switch (ch) {
                            case Event.LEFT:
                                state.moveLeft();
                                break;
                                /*
                                if ((figure.getX()>1) && (fNew[figure.getX()-1][figure.getY()+2]==0)) {
                                    HideFigure(figure);
                                    figure.setX(figure.getX()-1);
                                    DrawFigure(figure);
                                }
                                break;
                             */
                            case Event.RIGHT:
                                state.moveRight();
                                break;
                                /*
                                if ((figure.getX()<Width) && (fNew[figure.getX()+1][figure.getY()+2]==0)) {
                                    HideFigure(figure);
                                    figure.setX(figure.getX()+1);
                                    DrawFigure(figure);
                                }
                                break;
                                 */
                            case Event.UP:
                                state.getFigure().shiftUp();
                                /*
                                i = figure.c[1];
                                figure.c[1] = figure.c[2];
                                figure.c[2] = figure.c[3];
                                figure.c[3] = i;

                                 */
                                painter.drawFigure(state.getFigure());
                                break;
                            case Event.DOWN:
                                state.getFigure().shiftDown();
                                /*
                                i = figure.c[1];
                                figure.c[1] = figure.c[3];
                                figure.c[3] = figure.c[2];
                                figure.c[2] = i;
                                 */
                                painter.drawFigure(state.getFigure());
                                break;
                            case ' ':
                                performDrop();
                                break;
                                /*
                                HideFigure(figure);
                                DropFigure(figure);
                                DrawFigure(figure);
                                timeCount = 0;
                                break;

                                 */
                            //case 'P':
                            case 'p':
                                pauseGame();
                                break;
                                /*
                                while (!keyPressed) {
                                    HideFigure(figure);
                                    Delay(500);
                                    DrawFigure(figure);
                                    Delay(500);
                                }
                                timeCount = System.currentTimeMillis();
                                break;
                                 */
                            case '-':
                                state.changeLevel(-1);
                                break;
                                /*
                                if (Level > 0) Level--;
                                k=0;
                                ShowLevel(graphics);
                                break;
                                 */
                            case '+':
                                state.changeLevel(1);
                                break;
                                /*
                                if (Level < MaxLevel) Level++;
                                k=0;
                                ShowLevel(graphics);
                                break;
                                 */
                        }
                    }
                } while ( (int)(System.currentTimeMillis()- timeCount) <= (State.MaxLevel- state.getLevel())*TimeShift+MinTimeShift );
            };
            state.PasteFigure();
            do {
                state.checkForChange();
            } while (!state.getChangeStatus());
        }while (!state.fullField()&&gameOn);
    }


    /*
    private void checkForChange(){
        noChanges = true;
        TestField();
        if (!noChanges) {
            updateInfo();
        }
    }

    private void updateInfo(){
        delay(500);
        PackField();
        painter.drawField(fNew);
        score += dScore;
        painter.showScore(score);
        if (k >= FigToDrop) {
            k = 0;
            if (level <MaxLevel) level++;
            painter.showLevel(level);
        }
    }

     */

    /*
    private void changeLevel(int sign){
        if (level < MaxLevel) level +=sign;
        k=0;
        painter.showLevel(level);
    }

     */

    private void pauseGame(){
        while (!keyPressed) {
            painter.hideFigure(state.getFigure());
            delay(500);
            painter.drawFigure(state.getFigure());
            delay(500);
        }
        timeCount = System.currentTimeMillis();
    }

    private void performDrop(){
        state.drop();
        timeCount = 0;
    }

    /*
    private void moveRight(){
        if ((figure.getX()<Width) && (fNew[figure.getX()+1][figure.getY()+2]==0)) {
            painter.hideFigure(figure);
            figure.setX(figure.getX()+1);
            painter.drawFigure(figure);
        }
    }

     */

    /*
    private void moveLeft(){
        if ((figure.getX()>1) && (fNew[figure.getX()-1][figure.getY()+2]==0)) {
            painter.hideFigure(figure);
            figure.setX(figure.getX()-1);
            painter.drawFigure(figure);
        }
    }
     */
/*
    void ShowHelp(Graphics g) {
        g.setColor(Color.black);
        
        g.drawString(" Keys available:",200-LeftBorder,102);
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
        g.drawString("Level: "+Level,LeftBorder+100,400);
    }
    void ShowScore(Graphics g) {
        g.setColor(Color.black);
        g.clearRect(LeftBorder,390,100,20);
        g.drawString("Score: "+ score,LeftBorder,400);
    }

 */

    @Override
    public void start() {
        //		setBackground (new Color(180,180,180));
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void stop() {
        gameOn = false;
        thread = null;
        /*
        if (thread != null) {
            thread.stop();
            thread = null;
        }
         */
    }

    /*
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

     */
}