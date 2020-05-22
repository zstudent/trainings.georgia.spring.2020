package GameOfLife;

public class Model {
    private Logic logic;
    private ModelListener listener;

    public Model(){
        logic = new Logic(new State(30,30));
    }

    public void timePasses(){

    }

    public void setModelListener(ModelListener listener){
        this.listener = listener;
    }

    public void updateView(){
        listener.updateView();
    }
}
