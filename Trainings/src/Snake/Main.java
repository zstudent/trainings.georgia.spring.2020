package Snake;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::setup);
    }


    private static void setup()
    {
        JFrame frame = new JFrame("Snake");
        frame.setLayout(new BorderLayout());
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        View view = new View();
        frame.add(view, BorderLayout.CENTER);
        Model model = new Model(15,15);
        Controller controller = new Controller(model,view);
        FollowWay.followWay = ()->{controller.moveRight();};
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        controller.moveRight();
                        FollowWay.followWay=()->{controller.moveRight();};
                        break;
                    case KeyEvent.VK_LEFT:
                        controller.moveLeft();
                        FollowWay.followWay=()->{controller.moveLeft();};
                        break;
                    case KeyEvent.VK_UP:
                        controller.moveUp();
                        FollowWay.followWay=()->{controller.moveUp();};
                        break;
                    case KeyEvent.VK_DOWN:
                        controller.moveDown();
                        FollowWay.followWay=()->{controller.moveDown();};
                        break;
                }
            }
        });

        new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(FollowWay.followWay::move);
            }
        }).start();
        frame.setVisible(true);
    }
}
