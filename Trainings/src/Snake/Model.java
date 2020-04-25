package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements SnakeAteAppleListener{
    //All calculations are accumulated in logic object.
    private  Logic logic;
    //List of listeners to this Model.Controller is later added in this list.
    List<ModelListener> listeners = new ArrayList<>();

    public Model(int snakeRow,int snakeCol)
    {
        logic = new Logic(new State(snakeRow,snakeCol));
        logic.setListener(this);
    }

    public void moveLeft()
    {
        if(logic.moveLeft())
            refreshView();
        else fireGameOver();
    }

    public void moveRight()
    {
        if(logic.moveRight())
            refreshView();
        else fireGameOver();
    }

    public void moveDown()
    {
        if(logic.moveDown())
            refreshView();
        else fireGameOver();
    }

    public void moveUp()
    {
        if(logic.moveUp())
            refreshView();
        else fireGameOver();
    }

    //Add new model listeners(In our case Controller is one being added).
    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }

    //Notify Model listeners that model was updated/changed.
    public void refreshView()
    {
        for(ModelListener listener : listeners)
        {
            listener.onChange(logic.getBoard());
        }
    }

    //Notify Model listeners that game is over.
    public void fireGameOver()
    {
        for(ModelListener listener : listeners)
        {
            listener.fireLableChange("Game over");
        }
    }
    //Notify listeners to increase speed.
    @Override
    public void increaseSpeed() {
        for(ModelListener listener : listeners)
        {
            listener.increaseSpeed();
        }
    }
}
