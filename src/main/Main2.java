package main;

import java.io.IOException;
import java.net.ServerSocket;

import server.Server.ServerThread;

public class Main2 {

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
