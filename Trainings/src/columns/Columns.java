package columns;
import java.applet.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class Columns extends Applet{
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
    
    int Level, i, j, ii, k, ch;
    long Score, DScore, tc;
    Font fCourier;
    Figure Fig;
    int newField[][],oldField[][];
    boolean NoChanges = true, paused = false;
    Graphics graphics;
    View view;
    
    
    void checkNeighbours(int a, int b, int c, int d, int i, int j) {
        if ((newField[j][i]==newField[a][b]) && (newField[j][i]==newField[c][d])) {
            oldField[a][b] = 0;
            view.DrawBox(a,b,8);
            oldField[j][i] = 0;
            view.DrawBox(j,i,8);
            oldField[c][d] = 0;
            view.DrawBox(c,d,8);
            NoChanges = false;
            Score += (Level+1)*10;
            k++;
        };
    }
    void Delay(long t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException e) {};
    }
    void dropFigure(Figure f) {
    	int zz;
        if (f.y < HEIGHT-2) {
            zz = HEIGHT;
            while (newField[f.x][zz]>0) zz--;
            DScore = (((Level+1)*(HEIGHT*2-f.y-zz) * 2) % 5) * 5;
            f.y = zz-2;
        }
    }
    boolean isFieldFull() {
    	int i;
        for (i=1; i<=WIDTH; i++) {
            if (newField[i][3]>0)
                return true;
        }
        return false;
    }

    public void init() {
        newField = new int[WIDTH+2][HEIGHT+2];
        oldField = new int[WIDTH+2][HEIGHT+2];
        Level = 0;
        Score = 0;
        graphics = getGraphics();
        this.view = new View(graphics);
        addKeyListener(new KeyAdapter() {
        	@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					moveColumnLeft();
					break;
				case KeyEvent.VK_RIGHT:
					moveColumnRight();
					break;
				case KeyEvent.VK_DOWN:
					switchCubeDown();
					break;
				case KeyEvent.VK_UP:
					switchCubeDown();
					break;
				case KeyEvent.VK_P:
					pauseGame();
					break;
				case KeyEvent.VK_SPACE:
					dropColumn();
					break;
				case KeyEvent.VK_MINUS:
					decreaseLevel();
					break;
				case KeyEvent.VK_EQUALS:
					increaseLevel();
					break;
				default:
					break;
				}
			}

        });

        Thread thread = new Thread(() ->  {
        	while(!isFieldFull()) {
        		new Figure();
        		view.DrawFigure(Fig);
        		while((Fig.y<HEIGHT-2) && (newField[Fig.x][Fig.y+3]==0)) {
        			try {
						Thread.sleep(MaxLevel - Level == 0 ? 300 : 300 * (MaxLevel - Level));
					} catch (Exception e) {
						// TODO: handle exception
					}
        			if(!paused) moveColumn();
        		};
        		pasteFigure(Fig);
                do {
                    NoChanges = true;
                    testField();
                    if (!NoChanges) {
                        Delay(500);
                        packField();
                        view.DrawField(newField);
                        Score += DScore;
                        view.ShowScore(Score);
                        if (k>=FigToDrop) {
                            k = 0;
                            if (Level<MaxLevel) Level++;
                            view.ShowLevel(Level);
                        }
                    }
                } while (!NoChanges);
        	}
        });
        thread.setDaemon(true);
        thread.start();
        
    }
	public void moveColumn() {
		if((Fig.y<HEIGHT-2) && (newField[Fig.x][Fig.y+3]==0)) {
			view.HideFigure(Fig);
			Fig.y++;
			view.DrawFigure(Fig);			
		}
	}
    void packField() {
    	int i,j,n;
        for (i=1; i<=WIDTH; i++) {
            n = HEIGHT;
            for (j=HEIGHT; j>0; j--) {
                if (oldField[i][j]>0) {
                    newField[i][n] = oldField[i][j];
                    n--;
                }
            };
            for (j=n; j>0; j--) newField[i][j] = 0;
        }
    }
    public void paint(Graphics g) {
        //		ShowHelp(g);
        
//        g.setColor(Color.black);
        
        view.ShowLevel(Level);
        view.ShowScore(Score);
        view.DrawField(newField);
        view.DrawFigure(Fig);
        requestFocus();
    }
    void pasteFigure(Figure f) {
        newField[f.x][f.y] = f.c[1];
        newField[f.x][f.y+1] = f.c[2];
        newField[f.x][f.y+2] = f.c[3];
    }

	public void increaseLevel() {
		if(Level < MaxLevel) Level++;
		k = 0;
		view.ShowLevel(Level);
	}

	public void decreaseLevel() {
		if(Level > 0) Level--;
		k = 0;
		view.ShowLevel(Level);
	}
	public void pauseGame() {
		if(paused) paused = false;
		else paused = true;
	}

	public void dropColumn() {
		view.HideFigure(Fig);
		dropFigure(Fig);
		view.DrawFigure(Fig);
	}
	public void moveColumnRight() {
		if ((Fig.x<WIDTH) && (newField[Fig.x+1][Fig.y+2]==0)) {
			view.HideFigure(Fig);
		    Fig.x++;
		    view.DrawFigure(Fig);
		}
	}
	public void moveColumnLeft() {
		if ((Fig.x>1) && (newField[Fig.x-1][Fig.y+2]==0)) {
		    view.HideFigure(Fig);
		    Fig.x--;
		    view.DrawFigure(Fig);
		}
	}
	public void switchCubeDown() {
		i = Fig.c[1];
		Fig.c[1] = Fig.c[3];
		Fig.c[3] = Fig.c[2];
		Fig.c[2] = i;
		view.DrawFigure(Fig);
	}
	public void switchCubeUp() {
		i = Fig.c[1];
		Fig.c[1] = Fig.c[2];
		Fig.c[2] = Fig.c[3];
		Fig.c[3] = i;
		view.DrawFigure(Fig);
	}
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
    void testField() {
        int i,j;
        for (i=1; i<=HEIGHT; i++) {
            for (j=1; j<=WIDTH; j++) {
                oldField[j][i] = newField [j][i];
            }
        }
        for (i=1; i<=HEIGHT; i++) {
            for (j=1; j<=WIDTH; j++) {
                if (newField[j][i]>0) {
                    checkNeighbours(j,i-1,j,i+1,i,j);
                    checkNeighbours(j-1,i,j+1,i,i,j);
                    checkNeighbours(j-1,i-1,j+1,i+1,i,j);
                    checkNeighbours(j+1,i-1,j-1,i+1,i,j);
                }
            }
        }
    }



	

}