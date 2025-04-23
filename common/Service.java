package rc.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Service implements AutoCloseable {

	private final Socket socket;
	private final ObjectOutputStream out;
	private final ObjectInputStream in;

	public Service(Socket socket) throws IOException {

		this.socket = socket;
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	public Object receiveMsg() throws ClassNotFoundException, IOException {
		if (socket.isClosed()) {
			System.out.println("Socket closed before receiving message.");
			return null;
		}
		System.out.println("Waiting for message...");
		return in.readObject();
	}

	public void sendMsg(Object obj) throws IOException {
		System.out.println("Sending message: " + obj);
		out.writeObject(obj);
		out.flush();
		System.out.println("Poruka poslata...");
	}

	@Override
	public void close() throws IOException {

		try (Socket s = socket; ObjectOutputStream o = out; ObjectInputStream i = in) {

		}
	}

}
