package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread {
	private Socket socket = null;

    public Server(Socket socket) {
    	super("Server");
    	this.socket = socket;
    }

    public void run() {
    	try {
    		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		
    		out.close();
    		in.close();
    		socket.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
