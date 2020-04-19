package lesson200415;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleConnectionToGoogleCom {
	
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("google.com", 80);
			System.out.println(socket);
			
			OutputStream output = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(output);
			pw.write("GET / HTTP/1.1\n\n");
			pw.flush();
			
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
