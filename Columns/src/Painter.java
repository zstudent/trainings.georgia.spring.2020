import java.awt.*;

public class Painter {
    Color[] MyStyles = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
            Color.yellow,Color.pink,Color.magenta,Color.black};

    static final int LeftBorder=2;
    static final int SL=25;
    static final int TopBorder=2;
    static final int Depth=15;
    static final int Width=7;

    private Graphics graphics;

    public Painter(Graphics graphics){
        this.graphics = graphics;
        graphics.setColor(Color.black);
    }

    public void DrawBox(int x, int y, int c) {
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

    public void DrawField(int[][] field) {
        for (int i=1; i<=Depth; i++) {
            for (int j=1; j<=Width; j++) {
                DrawBox(j,i, field[j][i]);
            }
        }
    }

   public void DrawFigure(Figure f) {
        for(int i = 0; i < 3; i++){
            DrawBox(f.getX(),f.getY()+i,f.getColumn(i+1));
        }
        /*
        DrawBox(f.x,f.y,f.c[1]);
        DrawBox(f.x,f.y+1,f.c[2]);
        DrawBox(f.x,f.y+2,f.c[3]);
         */
    }

    void HideFigure(Figure f) {
        for(int i = 0; i<3;i++){
            DrawBox(f.getX(), f.getY()+i,0);
        }
        /*
        DrawBox(f.x,f.y,0);
        DrawBox(f.x,f.y+1,0);
        DrawBox(f.x,f.y+2,0);
         */
    }



    void ShowLevel(int level) {
        graphics.setColor(Color.black);
        graphics.clearRect(LeftBorder+100,390,100,20);
        graphics.drawString("Level: "+ level,LeftBorder+100,400);
    }

    void ShowScore(long score) {
        graphics.setColor(Color.black);
        graphics.clearRect(LeftBorder,390,100,20);
        graphics.drawString("Score: "+ score,LeftBorder,400);
    }

    void ShowHelp() {
        graphics.setColor(Color.black);
        graphics.drawString(" Keys available:",200-LeftBorder,102);
        graphics.drawString("Roll Box Up:     ",200-LeftBorder,118);
        graphics.drawString("Roll Box Down:   ",200-LeftBorder,128);
        graphics.drawString("Figure Left:     ",200-LeftBorder,138);
        graphics.drawString("Figure Right:    ",200-LeftBorder,148);
        graphics.drawString("Level High/Low: +/-",200-LeftBorder,158);
        graphics.drawString("Drop Figure:   space",200-LeftBorder,168);
        graphics.drawString("Pause:           P",200-LeftBorder,180);
        graphics.drawString("Quit:     Esc or Q",200-LeftBorder,190);
    }
}