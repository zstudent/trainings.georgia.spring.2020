package Snake;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//Extends JPanel.
public class View extends JPanel{
    //Gaming Board
    private Board board;
    //Label for game over/score.
    private JLabel label;

    public View() {
        setLayout(new BorderLayout());
        label = new JLabel("Welcome!");
        this.add(label, BorderLayout.NORTH);
    }

    //Actual head function for displaying board.
    public void draw(Board board)
    {
        this.board = board;
        //Use overridden paint component and pass this object's graphics obj.
        paintComponent(this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBoard(board,g);
    }

    //Getting board and draw appropriate rectangles.
    private void drawBoard(Board board,Graphics g)
    {
        if(board==null) return;
        for(int i=0; i<board.getNumCols(); i++)
        {
            for (int j = 0; j < board.getNumRows(); j++) {
                g.setColor(board.getColor(j,i));
                g.fillRect(40+20*i,50+20*j,25,25);
            }
        }
    }

    //Changing text to JLabel
    public void drawLabel(boolean flag)
    {
        if(flag) label.setText("Game over!");
        else label.setText("Currently playing");
    }
}
