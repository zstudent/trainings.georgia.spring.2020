package Snake;

public class Controller implements ModelListener {
    private Model model;
    private View view;

    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        model.addListener(this);
    }


    public Model getModel()
    {
        return this.model;
    }


    public View getView()
    {
        return this.view;
    }


    public void moveLeft()
    {
        model.moveLeft();
    }

    public void moveRight()
    {
        model.moveRight();
    }

    public void moveDown()
    {
        model.moveDown();
    }

    public void moveUp()
    {
        model.moveUp();
    }

    @Override
    public void refreshBoard(Board board) {
        view.draw(board);
    }
}
