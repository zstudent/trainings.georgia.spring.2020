package lesson200416;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SimpleUIChatClient {

	private static final int DEFAULT_PORT = 80;
	private static PrintWriter pw;
	private static JTextArea chatText;

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: <servername> <port>");
			System.exit(0);
		}

		SwingUtilities.invokeLater(SimpleUIChatClient::setupUI);

		setupConnection(args);

	}

	private static void setupUI() {
		JFrame frame = new JFrame("Chat");
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		chatText = new JTextArea();
		chatText.setEditable(false);
		panel.add(chatText, BorderLayout.CENTER);

		JTextField inputText = new JTextField(30);
		inputText.addActionListener(e -> {
			String text = inputText.getText();
			inputText.setText("");
			pw.println(text);
			pw.flush();
		});
		panel.add(inputText, BorderLayout.SOUTH);

		panel.setPreferredSize(new Dimension(600, 600));

		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void setupConnection(String[] args) {
		int port = DEFAULT_PORT;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println(
					"port must be a valid number from 1 to 65565");
			System.exit(0);
		}

		String serverName = args[0];

		try {
			Socket socket = new Socket(serverName, port);
			System.out.println(socket);

			OutputStream output = socket.getOutputStream();
			pw = new PrintWriter(output);

			ExecutorService executor = Executors
					.newSingleThreadExecutor();
			executor.execute(() -> {
				try {
					serveConnection(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void serveConnection(Socket socket)
			throws IOException {
		System.out.println("listening for input from socket");
		InputStream input = socket.getInputStream();
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			chatText.append(line + "\n");
		}
	}

}
