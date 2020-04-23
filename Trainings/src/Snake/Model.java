package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private  Logic logic;

    List<ModelListener> listeners = new ArrayList<>();

    public Model(int snakeRow,int snakeCol)
    {
        logic = new Logic(new State(snakeRow,snakeCol));
    }

    public boolean moveLeft()
    {
        if(logic.moveLeft())
            refreshView();
        return true;
    }

    public void moveRight()
    {
        if(logic.moveRight())
            refreshView();
    }

    public boolean moveDown()
    {
        if(logic.moveDown())
            refreshView();
        return true;
    }

    public boolean moveUp()
    {
        if(logic.moveUp())
            refreshView();
        return true;
    }

    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }

    public void refreshView()
    {
        for(ModelListener listener : listeners)
        {
            listener.refreshBoard(logic.getBoard());
        }
    }

    public Board getBoard()
    {
        return logic.getBoard();
    }

    public Snake getSnake()
    {
        return logic.getSnake();
    }
}
