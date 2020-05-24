package GameOfLife;

public class Controller implements ModelListener {
    private Model model;
    private View view;

    public Controller(Model model,View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void updateView() {
        view.refreshBoardState();
    }
}
