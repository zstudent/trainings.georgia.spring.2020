package gameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {
private int lattice[][];
private int rows;
private int columns;


	public GameOfLife(int rows, int columns){
		if(rows <= 0 || columns <= 0)
		{
		}
		lattice = new int[rows][columns];
		this.rows = rows;
		this.columns = columns;
	};
	

	public GameOfLife(int [][] initialState){
		this.rows = initialState.length;
		this.columns = initialState[0].length;
		lattice = new int[rows][columns];
		
		if(rows <= 0 || columns <= 0 || rows != columns )
		{
			throw new IllegalArgumentException("rows and columns must both be nonzero and equal length. rows: "+rows+" , columns: "+columns);
		}
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				lattice[row][column] = initialState[row][column];
			}
		}	
		
	};

	public int getStateAt(int row, int column)
	{
		return lattice[row][column];
	}
	

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}

}
