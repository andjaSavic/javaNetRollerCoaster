package rc.server;

import java.io.IOException;
import java.net.*;

import rc.common.RCInterface;

public class Server {
	
	int port;
	int cap;
	
	
	public Server(int port, int cap) {
	
		this.port = port;
		this.cap = cap;
		
	}

	
	
	private static void run(int port,int CAP) {
		RCInterface rc = new RollercoasterMonitor(CAP);
		try(ServerSocket server = new ServerSocket(port)){
			while(true) {
				Socket client = server.accept();
				new RequestHandler(client, rc).start();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		//prosledi port i kapacitet vozila
		run(5555,8);
	}
}

