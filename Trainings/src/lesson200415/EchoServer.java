package lesson200415;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Start echo server");
		try {
			ServerSocket ss = new ServerSocket(10001);
			while (true) {
				Socket socket = ss.accept();
				System.out.println(socket);
				
				new Thread(() -> {
					try {
						communicate(socket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}).start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void communicate(Socket socket) throws IOException {
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		Scanner scanner = new Scanner(socket.getInputStream());

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(" > " + line);
			pw.write(line + "\n");
			pw.flush();
		}
	}

}
