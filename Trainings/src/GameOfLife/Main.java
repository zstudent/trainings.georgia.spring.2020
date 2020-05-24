package GameOfLife;

import GameOfLife.MVC.Controller;
import GameOfLife.MVC.Model;
import GameOfLife.MVC.View;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GameOfLife");
        frame.setSize(new Dimension(800,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View view = new View();
        frame.add(view);
        Model model = new Model();
        Controller controller = new Controller(model,view);
        frame.setVisible(true);
        controller.startProcessingCells();
    }
}
