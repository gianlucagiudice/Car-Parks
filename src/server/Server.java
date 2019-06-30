package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static class ServerThread extends Thread {
		private Socket socket = null;
		
		public ServerThread(Socket socket) {
			super("ServerThread");
			this.socket = socket;
		}
		
		public void run() {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		boolean listening = true;
		
		try {
			serverSocket = new ServerSocket(0);
		} catch (IOException e) {
			System.err.println("Could not listen on any port.");
			System.exit(-1);
		}
			while (listening)
				try {
					new ServerThread(serverSocket.accept()).start();
				} catch (IOException e) {
					System.err.println("Accept failed.");
					System.exit(-1);
				}
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
