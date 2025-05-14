package echo;

import java.io.*;
import java.net.*;

public class Correspondent {
	protected Socket sock;
	protected BufferedReader sockIn;
	protected PrintWriter sockOut;

	public Correspondent() { } // init fields later
	public Correspondent(Socket s) {
		sock = s;
		initStreams();
	}
	
	public void setSocket(Socket socket) {
		this.sock = socket;
		initStreams();
	}

	protected void initStreams() {
		try {
			sockIn = 
					new BufferedReader(
					new InputStreamReader(
							sock.getInputStream()));
			sockOut = new PrintWriter(
					sock.getOutputStream(), true);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	protected void close() {
		try {
			sock.close();
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void requestConnection(String host, int port) {
		try {
			sock = new Socket(host, port);
			initStreams();
		} catch(UnknownHostException uhe) {
			System.err.println("unknown host " + uhe);
			System.exit(1);
		} catch(IOException ioe) {
			System.err.println("failed to create streams " + ioe);
			System.exit(1);
		}
	}

	public void send(String request) {
		sockOut.println(request);
	}

	public String receive() {
		String msg = null;
		try {
			msg = sockIn.readLine();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		return msg;
	}

	/* Lab 6: Future assignment?
	1. Add a method to write an object to the socket.
	Add object input and output streams to the Correspondent class. 
	Initialize them with the socket's input/output streams in the initStreams method.
	Provide methods:
	    void writeObject(Object msg) { ... }
		Object readObject() { ... }

	2. Add Message.java class to the echo package.
	3. Modify SimpleClient so that it wraps the user input in a message, 
	then sends it to the server using sendObject:
		sendObject(new Message<String>(msg));
	Use receivedObject to receive the server's response.

	4. Also modify RequestHandler so that it receives requests using requestObject. 
	If the request is an instance of Message<String>, then pass its content to the response method. 
	Wrap the return value into a message and send it back to the client using sendObject.
	5. Test using the MathHandler and the CacheHandler.
	*/
} 
