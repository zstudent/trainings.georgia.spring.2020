import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Universe extends JComponent {
    private final int EDGE_LENGTH = 10;
    private final int NODE_NUM = 50;
    private final int PANEL_WIDTH = 70;
    private int delay = 100;
    private JFrame frame;
    private JButton start;
    private JButton stop;
    private JButton nextStep;
    private JButton pause;
    private Timer timer;
    private static State state;
    private boolean gameOn;

    public void setUpButtons(){
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop.setEnabled(true);
                pause.setEnabled(true);
                start.setEnabled(false);
                nextStep.setEnabled(false);
                timer.start();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                stop.setEnabled(false);
                start.setEnabled(true);
                nextStep.setEnabled(true);
                pause.setEnabled(true);
                state = new State();
                repaint();
            }
        });

        nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   timer.stop();
                   start.setEnabled(true);
                   nextStep.setEnabled(true);
            }
        });
    }

    private void tick(){
        state.timePasses();
        repaint();
    }

    JPanel buttonPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, this.getHeight()));
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        start = new JButton("start");
        stop = new JButton("stop");
        nextStep = new JButton("next");
        pause = new JButton("pause");
        panel.setEnabled(false);
        stop.setEnabled(false);
        setUpButtons();
        panel.add(Box.createVerticalStrut(10));
        panel.add(start);
        panel.add(Box.createVerticalStrut(10));
        panel.add(pause);
        panel.add(Box.createVerticalStrut(10));
        panel.add(stop);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nextStep);
        return panel;
    }

    private static Node getCoordinates(int x, int y){
        return new Node(x/10, y/10);
    }

    private static JFrame createFrame(Universe u){
        JFrame frame = new JFrame("Game of Life");
        JComponent container = (JComponent)frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(u, "Center");
        container.add(u.buttonPanel(), "East");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }

    public Universe(){
        Dimension dimension = new Dimension(NODE_NUM * EDGE_LENGTH, NODE_NUM * EDGE_LENGTH + PANEL_WIDTH);
        this.setPreferredSize(dimension);
        state = new State();
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Universe.this.tick();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state.clicked(getCoordinates(e.getX(), e.getY()));
                repaint();
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);
        for(Node n : state.board){
            g.fillRect(EDGE_LENGTH* n.getX(), EDGE_LENGTH * n.getY(), EDGE_LENGTH, EDGE_LENGTH);
        }
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        Universe u = new Universe();
        JFrame frame = createFrame(u);
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
