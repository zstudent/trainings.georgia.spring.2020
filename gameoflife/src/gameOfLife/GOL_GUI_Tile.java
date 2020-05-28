package gameOfLife;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GOL_GUI_Tile extends JLabel implements MouseListener {
	
	private static final long serialVersionUID = 7832885560545657999L;
    Dimension minSize = new Dimension(1,1);
    GameOfLife model;
    int row_id, column_id;

    public GOL_GUI_Tile(int row_id, int column_id, GameOfLife model) {
    	this.row_id = row_id;
    	this.column_id = column_id;
    	this.model = model;
    	if(model.getStateAt(row_id, column_id) == 1)
		{
			setBackground(Color.yellow);
		}
		else
		{
			setBackground(Color.white);
		}
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.yellow));
    }


	public void paint(Graphics g)
	{
		//determine color
		if(model.getStateAt(row_id, column_id) == 1)
		{
			setBackground(Color.red);
		}
		else
		{
			setBackground(Color.yellow);
		}
		super.paint(g);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}