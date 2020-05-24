package GameOfLife.MVC;

import GameOfLife.MVC.Helpers.State;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private State state;

    public View(){
        super();
    }

    public void refreshBoardState(State state){
        this.state = state;
        paintComponent(getGraphics());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawState(state,g);
    }

    private void drawState(State state,Graphics g){
        for(int row =0; row<state.getGameBoardRows(); row++){
            for(int col=0; col<state.getGameBoardColumns(); col++){
                drawColoredCell(state, g, row, col);
            }
        }
    }

    private void drawColoredCell(State state, Graphics g, int row, int col) {
        setColorOfCell(state, g, row, col);
        g.fillRect(40+20*col,40+20*row,25,25);
    }

    private void setColorOfCell(State state, Graphics g, int row, int col) {
        if(state.isCellAlive(row,col)){
            g.setColor(Color.BLACK);
        }else{
            g.setColor(Color.WHITE);
        }
    }
}
