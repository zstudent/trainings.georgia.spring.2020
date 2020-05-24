package GameOfLife;

public class Model implements GenerationWasUpdatedListener{
    private Logic logic;
    private ModelListener listener;

    public Model(){
        logic = new Logic(new State(30,30),this);
    }

    public void setModelListener(ModelListener listener){
        this.listener = listener;
    }

    public void updateView(){
        listener.updateView();
    }

    @Override
    public void generationWasUpdated() {
        listener.updateView();
    }
}
