package Snake;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    //private Draw draw;
    private Board board;

    public View()
    {
        //this.draw = draw;
    }

    public void draw(Board board)
    {
        this.board = board;
        paintComponent(this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBoard(board,g);
    }

    private void drawBoard(Board board,Graphics g)
    {
        if(board==null) return;
        for(int i=0; i<board.getNumCols(); i++)
        {
            for (int j = 0; j < board.getNumRows(); j++) {
                g.setColor(board.getColor(j,i));
                g.fillRect(20*i,20*j,25,25);
            }
        }
    }

}
