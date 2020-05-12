package columns;
import java.applet.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class Columns extends Applet{
    
    Graphics graphics;
    View view;
    

    public void init() {
        graphics = getGraphics();
        this.view = new View(graphics);
        Controller controller = new Controller();
        controller.set(new Model(), view);
        addKeyListener(new KeyAdapter() {
        	@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					controller.moveColumnLeft();
					break;
				case KeyEvent.VK_RIGHT:
					controller.moveColumnRight();
					break;
				case KeyEvent.VK_DOWN:
					controller.switchCubeDown();
					break;
				case KeyEvent.VK_UP:
					controller.switchCubeUp();
					break;
				case KeyEvent.VK_P:
					controller.pauseGame();
					break;
				case KeyEvent.VK_SPACE:
					controller.dropColumn();
					break;
				case KeyEvent.VK_MINUS:
					controller.decreaseLevel();
					break;
				case KeyEvent.VK_EQUALS:
					controller.increaseLevel();
					break;
				default:
					break;
				}
			}

        });

        Thread thread = new Thread(() ->  {
        	while(!controller.isFieldFull()) {
        		new Figure();
        		while(controller.isFigureFitTheField()) {
        			try {
						Thread.sleep(controller.gameSpeed());
					} catch (Exception e) {
						// TODO: handle exception
					}
        			if(!controller.isPaused()) controller.moveColumn();
        		};
        		controller.pasteFigure();
        		controller.testField();
        		controller.removeNeighbours();

        	}
        });
        thread.setDaemon(true);
        thread.start();
        
    }
//    public void paint(Graphics g) {
//        //		ShowHelp(g);
//        
////        g.setColor(Color.black);
//        
//        view.ShowLevel(Level);
//        view.ShowScore(Score);
//        view.DrawField(newField);
//        view.DrawFigure(Fig);
//        requestFocus();
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

}