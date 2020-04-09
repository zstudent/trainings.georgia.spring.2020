package lesson200408;

import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ObserverExamples {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Hello");
		
		frame.setSize(400, 400);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Button button = new Button("Click me");
		button.addActionListener(e -> {
			System.out.println("pressed " + e);
		});
		
		button.addActionListener(e -> {
			System.out.println("oh, really?");
		});
		
		frame.add(button);
		frame.pack();
		
		frame.setVisible(true);
		
		
	}

}
