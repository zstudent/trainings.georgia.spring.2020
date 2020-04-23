package Snake;

import java.awt.*;

public class Logic {
    private State state;

    public Logic(State state)
    {
        this.state = state;
    }

    public boolean moveRight()
    {
        int col = state.getStateSnake().getHeadCol();
        int row = state.getStateSnake().getHeadRow();
        state.getStateBoard().setColor(row,col,Color.BLACK);
        col++;
        if(col>=state.getStateBoard().getNumCols())
        {
            col = 0;
        }
        state.getStateBoard().setColor(row,col,Color.RED);
        state.getStateSnake().setHeadCol(col);
        return true;
    }

    public boolean moveLeft()
    {
        int col = state.getStateSnake().getHeadCol();
        int row = state.getStateSnake().getHeadRow();
        state.getStateBoard().setColor(row,col,Color.BLACK);
        col--;
        if(col<0)
        {
            col = state.getStateBoard().getNumCols()-1;
        }
        state.getStateBoard().setColor(row,col,Color.RED);
        state.getStateSnake().setHeadCol(col);
        return true;
    }


    public boolean moveDown()
    {
        int col = state.getStateSnake().getHeadCol();
        int row = state.getStateSnake().getHeadRow();
        state.getStateBoard().getCell(row,col).setColor(Color.BLACK);
        row++;
        if(row>=state.getStateBoard().getNumCols())
        {
            row = 0;
        }
        state.getStateBoard().getCell(row,col).setColor(Color.RED);
        state.getStateSnake().setHeadRow(row);
        return true;

    }


    public boolean moveUp()
    {
        int col = state.getStateSnake().getHeadCol();
        int row = state.getStateSnake().getHeadRow();
        state.getStateBoard().getCell(row,col).setColor(Color.BLACK);
        row--;
        if(row<0)
        {
            row = state.getStateBoard().getNumRows()-1;
        }
        state.getStateBoard().getCell(row,col).setColor(Color.RED);
        state.getStateSnake().setHeadRow(row);
        return true;

    }

    public Board getBoard()
    {
        return state.getStateBoard();
    }

    public Snake getSnake()
    {
        return state.getStateSnake();
    }

}
