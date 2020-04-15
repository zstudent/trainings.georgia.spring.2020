package lesson200415;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleChatServer {

	private static final int DEFAULT_PORT = 10000;

	public static void main(String[] args) {
		System.out.println("Start chat server at " + DEFAULT_PORT);
		try {
			ServerSocket ss = new ServerSocket(DEFAULT_PORT);
			while (true) {
				Socket socket = ss.accept();
				System.out.println(socket);
				new Thread(() -> {
					try {
						communicate(socket, SimpleChatServer::sendToAll);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// functional description: String -> ()
	static void sendToAll(String info) {
		for (ClientConnection connection : clients) {
			connection.send(info);
		}
	}
	
	static List<ClientConnection> clients = new ArrayList<ClientConnection>();
	
	private static void communicate(Socket socket, Broadcaster broadcaster) throws IOException {
		ClientConnection client = new ClientConnection(socket);
		clients.add(client);
		client.read(broadcaster);
	}

}
