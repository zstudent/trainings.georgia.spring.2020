package GameOfLife.MVC;

import GameOfLife.MVC.Helpers.ModelListener;
import GameOfLife.MVC.Helpers.State;

public class Controller implements ModelListener {
    private Model model;
    private View view;

    public Controller(Model model,View view){
        this.model = model;
        this.view = view;
        model.setModelListener(this);
    }

    public void startProcessingCells(){
        model.startProcessingCells();
    }

    @Override
    public void updateView(State state) {
        view.refreshBoardState(state);
    }
}
