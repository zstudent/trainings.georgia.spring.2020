//package columns;
//import java.applet.*;
//import java.awt.*;
//import java.util.*;
//
//public class Columns extends Applet implements Runnable {
//    static final int
//    SL=25,
//    Depth=15,
//    Width=7,
//    MaxLevel=7,
//    TimeShift=250,
//    FigToDrop=33,
//    MinTimeShift=200,
//    LeftBorder=2,
//    TopBorder=2;
//    
//        Color MyStyles[] = {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
//    Color.yellow,Color.pink,Color.magenta,Color.black};
//    
//    int Level, i, j, ii, k, change;
//    long Score, DScore, tc;
//    Font fCourier;
//    Figure figure;
//    int Fnew[][],Fold[][];
//    boolean NoChanges = true, KeyPressed;
//    Graphics graphics;
//    
//    Thread thread;
//    
//        void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
//        if (Fnew[j][i]==Fnew[a][b] && Fnew[j][i]==Fnew[c][d]) {
//            Fold[a][b] = 0;
//            DrawBox(a,b,8);
//            Fold[j][i] = 0;
//            DrawBox(j,i,8);
//            Fold[c][d] = 0;
//            DrawBox(c,d,8);
//            NoChanges = false;
//            Score += (Level+1)*10;
//            k++;
//        }
//    }
//    void Delay(long t) {
//        try {
//            Thread.sleep(t);
//        }
//        catch (InterruptedException e) {}
//    }
//    
//    void fillRectangle(int x, int y, int coordinate) {
//    	graphics.fillRect(LeftBorder+x*SL-SL + coordinate, TopBorder+y*SL-SL + coordinate,
//    			SL - coordinate * 2, SL - coordinate * 2);
//    }
//    
//    void drawRectangle(int x, int y, int coordinate) {
//    	graphics.drawRect(LeftBorder+x*SL-SL + coordinate, TopBorder+y*SL-SL + coordinate,
//    			SL - coordinate * 2, SL - coordinate * 2);
//    }
//    
//    void DrawBox(int x, int y, int c) {
//        switch (c) {
//		case 0:
//			graphics.setColor(Color.black);
//            fillRectangle(x, y, 0);
//            drawRectangle(x, y, 0);
//			break;
//		case 8:
//			graphics.setColor(Color.white);
//            drawRectangle(x, y, 1);
//			drawRectangle(x, y, 2);
//			graphics.setColor(Color.black);
//            fillRectangle(x, y, 3);
//			break;
//		default:
//			graphics.setColor(MyStyles[c]);
//            fillRectangle(x, y, 0);
//			graphics.setColor(Color.black);
//            drawRectangle(x, y, 0);
//			break;
//		}
//        //		g.setColor (Color.black);
//    }
//    void DrawField(Graphics graphics) {
//        for (int i=1; i<=Depth; i++) {
//            for (int j=1; j<=Width; j++) {
//                DrawBox(j,i,Fnew[j][i]);
//            }
//        }
//    }
//    void DrawFigure(Figure figure) {
//        DrawBox(figure.xCoordinate,figure.yCoordinate,figure.color[1]);
//        DrawBox(figure.xCoordinate,figure.yCoordinate+1,figure.color[2]);
//        DrawBox(figure.xCoordinate,figure.yCoordinate+2,figure.color[3]);
//    }
//    void DropFigure(Figure figure) {
//        int zz;
//        if (figure.yCoordinate < Depth-2) {
//            zz = Depth;
//            while (Fnew[figure.xCoordinate][zz]>0) {
//				zz--;
//			}
//            DScore = (((Level+1)*(Depth*2-figure.yCoordinate-zz) * 2) % 5) * 5;
//            figure.yCoordinate = zz-2;
//        }
//    }
//    boolean FullField() {
//        int i;
//        for (i=1; i<=Width; i++) {
//            if (Fnew[i][3]>0) {
//				return true;
//			}
//        }
//        return false;
//    }
//    void HideFigure(Figure figure) {
//        DrawBox(figure.xCoordinate,figure.yCoordinate,0);
//        DrawBox(figure.xCoordinate,figure.yCoordinate+1,0);
//        DrawBox(figure.xCoordinate,figure.yCoordinate+2,0);
//    }
//    public void init() {
//        Fnew = new int[Width+2][Depth+2];
//        Fold = new int[Width+2][Depth+2];
//        graphics = getGraphics();
//    }
//    public boolean keyDown(Event e, int k) {
//        KeyPressed = true;
//        change = e.key;
//        return true;
//    }
//    public boolean lostFocus(Event e, Object w) {
//        KeyPressed = true;
//        change = 'P';
//        return true;
//    }
//    void PackField() {
//        for (int i=1; i<=Width; i++) {
//            int n = Depth;
//            for (int j=Depth; j>0; j--) {
//                if (Fold[i][j]>0) {
//                    Fnew[i][n] = Fold[i][j];
//                    n--;
//                }
//            }
//            for (int j=n; j>0; j--) {
//				Fnew[i][j] = 0;
//			}
//        }
//    }
//    public void paint(Graphics graphics) {
//        //		ShowHelp(g);
//        
//        graphics.setColor(Color.black);
//        
//        ShowLevel(graphics);
//        ShowScore(graphics);
//        DrawField(graphics);
//        DrawFigure(figure);
//        requestFocus();
//    }
//    void PasteFigure(Figure f) {
//        Fnew[f.xCoordinate][f.yCoordinate] = f.color[1];
//        Fnew[f.xCoordinate][f.yCoordinate+1] = f.color[2];
//        Fnew[f.xCoordinate][f.yCoordinate+2] = f.color[3];
//    }
//    public void run() {
//        for (i=0; i<Width+1; i++){
//            for (j=0; j<Depth+1; j++) {
//                Fnew[i][j] = 0;
//                Fold[i][j] = 0;
//            }
//        }
//        Level = 0;
//        Score = 0;
//        j = 0;
//        k = 0;
//        graphics.setColor(Color.black);
//        requestFocus();
//        
//        do {
//            tc = System.currentTimeMillis();
//            new Figure();
//            DrawFigure(figure);
//            while (figure.yCoordinate<Depth-2 && Fnew[figure.xCoordinate][figure.yCoordinate+3]==0) {
//                if ((int)(System.currentTimeMillis()-tc)>(MaxLevel-Level)*TimeShift+MinTimeShift) {
//                    tc = System.currentTimeMillis();
//                    HideFigure(figure);
//                    figure.yCoordinate++;
//                    DrawFigure(figure);
//                }
//                DScore = 0;
//                do {
//                    Delay(50);
//                    if (KeyPressed) {
//                        KeyPressed = false;
//                        switch (change) {
//                            case Event.LEFT:
//                                if (figure.xCoordinate>1 && Fnew[figure.xCoordinate-1][figure.yCoordinate+2]==0) {
//                                    HideFigure(figure);
//                                    figure.xCoordinate--;
//                                    DrawFigure(figure);
//                                }
//                                break;
//                            case Event.RIGHT:
//                                if (figure.xCoordinate<Width && Fnew[figure.xCoordinate+1][figure.yCoordinate+2]==0) {
//                                    HideFigure(figure);
//                                    figure.xCoordinate++;
//                                    DrawFigure(figure);
//                                }
//                                break;
//                            case Event.UP:
//                                i = figure.color[1];
//                                figure.color[1] = figure.color[2];
//                                figure.color[2] = figure.color[3];
//                                figure.color[3] = i;
//                                DrawFigure(figure);
//                                break;
//                            case Event.DOWN:
//                                i = figure.color[1];
//                                figure.color[1] = figure.color[3];
//                                figure.color[3] = figure.color[2];
//                                figure.color[2] = i;
//                                DrawFigure(figure);
//                                break;
//                            case ' ':
//                                HideFigure(figure);
//                                DropFigure(figure);
//                                DrawFigure(figure);
//                                tc = 0;
//                                break;
//                            case 'P':
//                            case 'p':
//                                while (!KeyPressed) {
//                                    HideFigure(figure);
//                                    Delay(500);
//                                    DrawFigure(figure);
//                                    Delay(500);
//                                }
//                                tc = System.currentTimeMillis();
//                                break;
//                            case '-':
//                                if (Level > 0) {
//									Level--;
//								}
//                                k=0;
//                                ShowLevel(graphics);
//                                break;
//                            case '+':
//                                if (Level < MaxLevel) {
//									Level++;
//								}
//                                k=0;
//                                ShowLevel(graphics);
//                                break;
//                        }
//                    }
//                } while ( (int)(System.currentTimeMillis()-tc) <= (MaxLevel-Level)*TimeShift+MinTimeShift );
//            }
//            PasteFigure(figure);
//            do {
//                NoChanges = true;
//                TestField();
//                if (!NoChanges) {
//                    Delay(500);
//                    PackField();
//                    DrawField(graphics);
//                    Score += DScore;
//                    ShowScore(graphics);
//                    if (k>=FigToDrop) {
//                        k = 0;
//                        if (Level<MaxLevel) {
//							Level++;
//						}
//                        ShowLevel(graphics);
//                    }
//                }
//            } while (!NoChanges);
//        }while (!FullField());
//    }
//    void ShowHelp(Graphics g) {
//        g.setColor(Color.black);
//        
//        g.drawString(" Keys available:",200-LeftBorder,102);
//        g.drawString("Roll Box Up:     ",200-LeftBorder,118);
//        g.drawString("Roll Box Down:   ",200-LeftBorder,128);
//        g.drawString("Figure Left:     ",200-LeftBorder,138);
//        g.drawString("Figure Right:    ",200-LeftBorder,148);
//        g.drawString("Level High/Low: +/-",200-LeftBorder,158);
//        g.drawString("Drop Figure:   space",200-LeftBorder,168);
//        g.drawString("Pause:           P",200-LeftBorder,180);
//        g.drawString("Quit:     Esc or Q",200-LeftBorder,190);
//    }
//    void ShowLevel(Graphics graphics) {
//        graphics.setColor(Color.black);
//        graphics.clearRect(LeftBorder+100,390,100,20);
//        graphics.drawString("Level: "+Level,LeftBorder+100,400);
//    }
//    void ShowScore(Graphics graphics) {
//        graphics.setColor(Color.black);
//        graphics.clearRect(LeftBorder,390,100,20);
//        graphics.drawString("Score: "+Score,LeftBorder,400);
//    }
//    public void start() {
//        graphics.setColor(Color.black);
//        
//        //		setBackground (new Color(180,180,180));
//        
//        if (thread == null) {
//            thread = new Thread(this);
//            thread.start();
//        }
//    }
//    public void stop() {
//        if (thread != null) {
//            thread.stop();
//            thread = null;
//        }
//    }
//    void TestField() {
//        for (int i=1; i<=Depth; i++) {
//            for (int j=1; j<=Width; j++) {
//                Fold[j][i] = Fnew [j][i];
//            }
//        }
//        for (int i=1; i<=Depth; i++) {
//            for (int j=1; j<=Width; j++) {
//                if (Fnew[j][i]>0) {
//                    CheckNeighbours(j,i-1,j,i+1,i,j);
//                    CheckNeighbours(j-1,i,j+1,i,i,j);
//                    CheckNeighbours(j-1,i-1,j+1,i+1,i,j);
//                    CheckNeighbours(j+1,i-1,j-1,i+1,i,j);
//                }
//            }
//        }
//    }
//}