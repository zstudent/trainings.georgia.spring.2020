package Snake;

public interface ModelListener {
    void onChange(Board board);
    void fireLableChange(String msg);
    void increaseSpeed();
}
