package Snake;

//Controller/Middle man between Model and View.
public class Controller implements ModelListener {
    private int timer = 300;
    private Model model;
    private View view;

    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
        model.addListener(this);
    }

    //Movements of Snake.
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

    //Returns timer of game.
    public int getTimer(){ return this.timer; }

    //Notifies view about changes in Model.
    @Override
    public void onChange(Board board) { view.draw(board); }

    //Notifies view about that game is over.
    @Override
    public void fireLableChange(String msg){ view.drawLabel(msg); }

    @Override
    public void increaseSpeed() {
        timer*=0.9;
        view.drawLabel((Integer.parseInt(view.getLabel().getText()) + 10) + "");
    }
}
