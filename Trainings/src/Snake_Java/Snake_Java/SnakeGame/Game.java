package SnakeGame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import SnakeGame.SnakeBoard.Board;

public class Game{

    private static JFrame frame; 

    public Game() {

        frame = new JFrame("Snake");

        initialize();
    }

    private static void initialize() {
        frame.add(new Board());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void restart() {
        frame.dispose();
        frame = null;
        frame = new JFrame("Snake Game");

        initialize();
    }

    public static void end() {
        frame.dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Game game = new Game();
            }
        });
    }
}