import java.applet.*;
import java.awt.*;


public class Columns extends Applet implements Runnable {

    static final int TimeShift=250;
    static final int MinTimeShift=200;
    private int ch;
    private long timeCount;
    private boolean keyPressed;
    private boolean gameOn;
    private Painter painter;
    private State state;
    private Thread thread;

    public static void delay(long t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException ignored) {};
    }


    @Override
    public void init() {
        keyPressed = false;
        gameOn = true;
        painter = new Painter(getGraphics());
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

    @Override
    public void paint(Graphics g) {
        state.initialPaint();
        requestFocus();
    }

    public void run() {
        do {
            timeCount = System.currentTimeMillis();
            state.generateFigure();
            painter.drawFigure(state.getFigure());
            while (state.inRange()) {
                if (checkTime()) {
                    timeCount = System.currentTimeMillis();
                    painter.hideFigure(state.getFigure());
                    state.getFigure().setY(state.getFigure().getY()+1);
                    painter.drawFigure(state.getFigure());
                }
                state.setDsToZero();
                do {
                    delay(50);
                    parseEvent();
                } while (!checkTime());
            };
            state.PasteFigure();
            do {
                state.checkForChange();
            } while (!state.getChangeStatus());
        }while (!state.fullField()&&gameOn);
    }


    private boolean checkTime(){
        return (int)(System.currentTimeMillis()- timeCount)>
                (State.MaxLevel- state.getLevel())*TimeShift+MinTimeShift;
    }

    private void parseEvent(){
        if (keyPressed) {
            keyPressed = false;
            switch (ch) {
                case Event.LEFT:
                    state.moveLeft();
                    break;
                case Event.RIGHT:
                    state.moveRight();
                    break;
                case Event.UP:
                    state.getFigure().shiftUp();
                    painter.drawFigure(state.getFigure());
                    break;
                case Event.DOWN:
                    state.getFigure().shiftDown();
                    painter.drawFigure(state.getFigure());
                    break;
                case ' ':
                    performDrop();
                    break;
                case 'p':
                    pauseGame();
                    break;
                case '-':
                    state.changeLevel(-1);
                    break;
                case '+':
                    state.changeLevel(1);
                    break;
            }
        }
    }

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
    }

}