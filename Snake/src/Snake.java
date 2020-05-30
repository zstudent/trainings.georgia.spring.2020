import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Snake extends JComponent{
    static final int columns = 30;
    static final int rows = 20;
    static  final int rectEdge = 15;
    protected boolean gameOn;
    protected boolean gamePaused;
    protected  JButton startButton;
    protected  JButton stopButton;
    protected  JButton toggle;
    protected  JButton easy;
    protected  JButton medium;
    protected  JButton hard;
    static  boolean moveCount;
    static private int delay = 150;
    protected javax.swing.Timer timer;
    static private State state;
    static  private String difficulty;
    static private int diffCount;

    public Snake(int pixelSize){
        super();
        state = new State(rows, columns);
        Dimension dimension = new Dimension(columns*pixelSize,rows*pixelSize+20);
        setPreferredSize(dimension);
        gameOn = false;
        moveCount = true;
        diffCount = 2;
        timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
    }

    private void startGame(){
        state = new State(rows,columns);
        state.startGame();
        repaint();
        gameOn = true;
        setButtonState();
        gamePaused = false;
        toggle.setEnabled(true);
        timer.start();
        easy.setEnabled(false);
        medium.setEnabled(false);
        hard.setEnabled(false);
    }

    private void stopGame(){
        gameOn = false;
        timer.stop();
        gamePaused = true;
        togglePause();
        toggle.setEnabled(false);
        setButtonState();
        updateDifficulty();
    }

    private void setButtonState(){
        startButton.setEnabled(!gameOn);
        stopButton.setEnabled(gameOn);
    }

    private void togglePause(){
        if(gamePaused){
            timer.start();
            gamePaused = false;
            toggle.setText(" pause ");
            toggle.setFocusable(false);
        }else{
            timer.stop();
            toggle.setFocusable(false);
            gamePaused = true;
            toggle.setText("resume");
        }
    }

    private void updateDifficulty(){
        if(diffCount == 1){
            difficulty = "Easy";
            delay = 150;
            easy.setEnabled(false);
            medium.setEnabled(true);
            hard.setEnabled(true);
        }else if(diffCount == 2){
            difficulty = "Medium";
            delay = 100;
            easy.setEnabled(true);
            medium.setEnabled(false);
            hard.setEnabled(true);
        }else if(diffCount == 3){
            difficulty = "Hard";
            delay = 50;
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(false);
        }
        easy.setFocusable(false);
        medium.setFocusable(false);
        hard.setFocusable(false);
        timer.setDelay(delay);
        repaint();
    }

    public void tick(){
        if(!gameOn){
            return;
        }
        if(state.gameOver){
            stopGame();
        }
        state.move();
        repaint();
    }

    private void setupButtons(){
        startButton = new JButton("  start  ");
        startButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        stopButton = new JButton("  stop  ");
        stopButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopGame();
            }
        });
        setButtonState();
        toggle = new JButton(" pause ");
        toggle.setEnabled(false);
        toggle.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        });
        easy = new JButton("  Easy   ");
        easy.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                diffCount = 1;
                updateDifficulty();
            }
        });
        medium = new JButton("Medium");
        medium.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                diffCount = 2;
                updateDifficulty();
            }
        });
        hard = new JButton("  Hard   ");
        hard.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                diffCount = 3;
                updateDifficulty();
            }
        });
        updateDifficulty();
    }

    private JComponent createSidePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setupButtons();
        JLabel menu = new JLabel(" Menu");
        JLabel diffLabel = new JLabel("Difficulty");
        menu.setFont(new Font("Verdana", Font.PLAIN, 18));
        diffLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
        panel.add(menu);
        panel.add(Box.createVerticalStrut(20));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(stopButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(toggle);
        panel.add(Box.createVerticalStrut(15));
        panel.add(diffLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(easy);
        panel.add(Box.createVerticalStrut(15));
        panel.add(medium);
        panel.add(Box.createVerticalStrut(15));
        panel.add(hard);
        panel.add(Box.createHorizontalStrut(20));
        panel.setBackground(Color.gray);
        panel.setPreferredSize(new Dimension(80,getHeight()));
        return panel;
    }

    private static JFrame createFrame(Snake snake){
        JFrame frame = new JFrame("Snake");
        JComponent container = (JComponent)frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(snake, BorderLayout.CENTER);
        container.add(snake.createSidePanel(), BorderLayout.EAST);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(moveCount){
                            state.changeDirection(Direction.LEFT);
                            moveCount = false;
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if(moveCount){
                            state.changeDirection(Direction.RIGHT);
                            moveCount = false;
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if(moveCount){
                            state.changeDirection(Direction.DOWN);
                            moveCount = false;
                        }
                        break;

                    case KeyEvent.VK_UP:
                        if(moveCount){
                            state.changeDirection(Direction.UP);
                            moveCount = false;
                        }
                        break;

                    default:
                        break;
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }


    public void paintComponent(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(0, 0,getWidth(),20);
        g.setColor(Color.black);
        g.drawRect(0,0,getWidth()-1,getHeight());
        g.drawRect(0,0,getWidth()/2-61,getHeight());
        g.drawRect(0,0,getWidth()/2+61,getHeight());
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString("Score: " + state.score,5,15);
        g.drawString("Difficulty: " + difficulty,getWidth()-150,15);

        for(int x = 0; x<getWidth(); x+=rectEdge){
            for(int y = 20; y<getHeight(); y+=rectEdge){
                int i = x/15;
                int j = y/15-1;
                g.setColor(Color.black);
                g.fillRect(x, y,rectEdge,rectEdge);
                if(state.board.getBoard(j,i)==1 ){
                    g.setColor(Color.green);
                    g.fillRect(x, y,rectEdge-5,rectEdge-5);
                }else if(state.board.getBoard(j,i)==2 ){
                    g.setColor(Color.magenta);
                    g.fillOval(x, y,rectEdge-5,rectEdge-5);
                }else if(state.board.getBoard(j,i)==3){
                    g.setColor(Color.red);
                    g.fillRect(x, y,rectEdge-5,rectEdge-5);
                    g.setColor(Color.black);
                    g.drawString("Game Over",getWidth()/2 - 45,15);
                    stopGame();
                }
            }
        }
        moveCount = true;
    }


    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        Snake snake = new Snake(rectEdge);
        JFrame frame = Snake.createFrame(snake);
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
