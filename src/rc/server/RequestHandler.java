package rc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rc.common.RCInterface;


public class RequestHandler extends Thread {
	Socket sock;
	RCInterface rm;

	public RequestHandler(Socket c, RCInterface rm) {

		this.sock = c;
		this.rm = rm;
	}

	@Override
	public void run() {

		try (Socket client = this.sock;
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {

			System.out.println("Waiting for message from client...");
			String mess = (String) in.readObject();
			System.out.println("Received message: " + mess);

			String data[] = mess.split("#");
			String op = data[0];
			int id = Integer.parseInt(data[1]);
			System.out.println(op);
			System.out.println(data[1]);

			switch (op) {
			case "StartRide coaster": {
				rm.startRide(id);
				out.writeObject("Vozilo " + id +" se napunilo...");
				out.flush();
				break;
			}
			case "EndRide coaster": {
				rm.endRide(id);
				out.writeObject("Vozilo" + id+ " se ispraznilo...");
				out.flush();
				break;
			}
			case "Ride": {
				
				rm.ride(id);
				out.writeObject("Putnik" +id+ " se izvozao ...");
				out.flush();
				break;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + op);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection terminated");
		}
	}

}
