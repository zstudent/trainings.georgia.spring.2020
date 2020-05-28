package SnakeGame.SnakeBoard.SnakeAssets;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import SnakeGame.SnakeBoard.Board;

public class KeyChecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!Board.snake.isMovingRight())
                && (!Board.keyPressed)) {
            Board.snake.setMovingLeft();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!Board.snake.isMovingLeft())
                && (!Board.keyPressed)) {
            Board.snake.setMovingRight();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!Board.snake.isMovingDown()) && (!Board.keyPressed)) {
            Board.snake.setMovingUp();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!Board.snake.isMovingUp()) && (!Board.keyPressed)) {
            Board.snake.setMovingDown();
            Board.keyPressed = true;
        }
    }
}