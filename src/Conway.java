import javax.swing.*;
import java.awt.*;

public class Conway extends JComponent {
    private static final int DELAY = 100;
    private static final int SIZE = 40;
    private static final int BLOCK_SIZE = 15;
    private State state;

    public Conway(){
        super();
        setPreferredSize(new Dimension(BLOCK_SIZE * SIZE, BLOCK_SIZE * SIZE));
        state = new State(SIZE);

        new Timer(DELAY, e -> tick()).start();
    }

    private void tick() {
        state.checkWholeField();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Field field = state.getField();
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(field.getGridSpot(i, j)){
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect((BLOCK_SIZE * i), (BLOCK_SIZE * j), BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        Conway conway = new Conway();
        JFrame frame = Conway.createFrame(conway);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    private static JFrame createFrame(Conway conway) {
        JFrame frame = new JFrame("Game of life");
        JComponent container = (JComponent)frame.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(conway, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
