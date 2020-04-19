package lesson200416;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleCommandLineChatClient {
	
	private static final int DEFAULT_PORT = 80;

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Usage: <servername> <port>");
			System.exit(0);
		}
		
		int port = DEFAULT_PORT;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("port must be a valid number from 1 to 65565");
			System.exit(0);
		}
		
		String serverName = args[0];
		
		try {
			Socket socket = new Socket(serverName, port);
			System.out.println(socket);
			
			OutputStream output = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(output);
			
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(()->{
				try {
					serveConnection(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			Scanner keyboardScanner = new Scanner(System.in);
			
			while(keyboardScanner.hasNextLine()) {
				String line = keyboardScanner.nextLine();
				pw.println(line);
				pw.flush();
			}
			
			
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
			System.out.println(line);
		}
	}

}
