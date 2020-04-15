package lesson200415;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleConnectionToTimeServer {
	
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("time-a-g.nist.gov", 13);
			System.out.println(socket);
			InputStream input = socket.getInputStream();
			Scanner scanner = new Scanner(input);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
