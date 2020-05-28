package gameOfLife;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

public class GOL_GUI_GridContainer extends JPanel{
   

	Dimension minSize = new Dimension(1,1);
    
    GOL_GUI_Tile tileGrid[][];
    int rows, columns;
    GameOfLife model;

    public GOL_GUI_GridContainer(int rows, int columns, GameOfLife model) {
    	super(new GridLayout(rows,columns), true);
    	tileGrid = new GOL_GUI_Tile[rows][columns];
    	this.model = model;
    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column] = new GOL_GUI_Tile(row, column, model);
    			add(tileGrid[row][column]);
    		}
    		
    	}
    	this.rows = rows;
    	this.columns = columns;
    	
    	
    	
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

        @Override
	public void paint(Graphics g)
	{

    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column].repaint();
    		}
    		
    	}
    	
    	super.paint(g);
	}
    
}