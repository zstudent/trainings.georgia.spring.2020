package GameOfLife.MVC;

import GameOfLife.MVC.Helpers.GenerationWasUpdatedListener;
import GameOfLife.MVC.Helpers.Logic;
import GameOfLife.MVC.Helpers.ModelListener;
import GameOfLife.MVC.Helpers.State;

public class Model implements GenerationWasUpdatedListener {
    private Logic logic;
    private ModelListener listener;

    public Model(){
        setUpLogicOfGame();
    }

    public void setModelListener(ModelListener listener){
        this.listener = listener;
    }

    public void startProcessingCells(){
        logic.startProcessingCells();
    }

    @Override
    public void generationWasUpdated(State state) {
        listener.updateView(state);
    }

    private void setUpLogicOfGame() {
        logic = new Logic(new State(30,30));
        logic.setGenerationUpdatedListener(this);
    }
}
