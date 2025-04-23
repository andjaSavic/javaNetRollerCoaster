package rc.server;
import rc.common.RCInterface;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class RollerCoasterNet implements RCInterface {
	private String host;
	private int port;
	public RollerCoasterNet(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	public void ride(int id) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
			out.writeObject("Ride"+"#"+id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		
	}
	
	public void startRide(int id) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
			out.writeObject("StartRide coaster"+"#"+id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		
	}
	
	public void endRide(int kap) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
			out.writeObject("EndRide coaster"+"#"+kap);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		
	}

}
