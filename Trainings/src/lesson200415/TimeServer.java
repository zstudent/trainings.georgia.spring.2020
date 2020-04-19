package lesson200415;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(1313);
			System.out.println(ss);
			
			Socket socket = ss.accept();
			System.out.println(socket);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			
			pw.write(new Date().toString());
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
