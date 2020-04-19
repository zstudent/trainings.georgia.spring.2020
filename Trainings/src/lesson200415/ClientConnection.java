package lesson200415;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection {
	
	private Socket socket;
	private PrintWriter pw;
	private Scanner sc;

	public ClientConnection(Socket socket) throws IOException {
		this.socket = socket;
		pw = new PrintWriter(socket.getOutputStream());
		sc = new Scanner(socket.getInputStream());
	}
	
	public void send(String message) {
		pw.write(message + "\n");
		pw.flush();
	}
	
	public void read(Broadcaster br) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(" > " + line);
			br.broadcast(line);
		}

	}

}
