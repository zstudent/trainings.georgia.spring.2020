package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::setup);
    }


    private static void setup()
    {
        JFrame frame = new JFrame("MySnake");
        frame.setLayout(new BorderLayout());
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        View view = new View();
        frame.add(view);
        Model model = new Model(15,15);
        Controller controller = new Controller(model,view);
        //Initialise snake's initial follow pattern.
        LastDirection.followWay = ()->{controller.moveRight();};
        LastDirection.lastDirection = KeyEvent.VK_RIGHT;

        //All if() statements during listening to key presses
        //are to not let user press opposite or same direction button
        //to avoid poor UX.
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        if(LastDirection.lastDirection!=KeyEvent.VK_LEFT
                                && LastDirection.lastDirection!=KeyEvent.VK_RIGHT)
                        {
                            controller.moveRight();
                            LastDirection.followWay=()->{controller.moveRight();};
                            LastDirection.lastDirection = KeyEvent.VK_RIGHT;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(LastDirection.lastDirection!=KeyEvent.VK_RIGHT
                                && LastDirection.lastDirection!=KeyEvent.VK_LEFT)
                        {
                            controller.moveLeft();
                            LastDirection.followWay=()->{controller.moveLeft();};
                            LastDirection.lastDirection = KeyEvent.VK_LEFT;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(LastDirection.lastDirection!=KeyEvent.VK_DOWN
                                && LastDirection.lastDirection!=KeyEvent.VK_UP)
                        {
                            controller.moveUp();
                            LastDirection.followWay=()->{controller.moveUp();};
                            LastDirection.lastDirection = KeyEvent.VK_UP;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(LastDirection.lastDirection!=KeyEvent.VK_UP
                                && LastDirection.lastDirection!=KeyEvent.VK_DOWN)
                        {
                            controller.moveDown();
                            LastDirection.followWay=()->{controller.moveDown();};
                            LastDirection.lastDirection = KeyEvent.VK_DOWN;
                        }
                        break;
                }
            }
        });

        //Engine of Snake game, Ticks in every Controller.getTimer() m.second.
        new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(controller.getTimer());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Follow snake's last direction.
                LastDirection.followWay.move();
            }
        }).start();
        frame.setVisible(true);
    }

    //Holds data about last direction of snake, before user pressed key.
    public static class LastDirection {
        //Functional interface is used to make snake follow last direction.
        public static LastDirectionFollow followWay;
        //lastDirection variable is to not to let snake go on current direction's opposite side.
        public static int lastDirection;

        @FunctionalInterface
        public interface LastDirectionFollow {
            void move();
        }
    }
}
