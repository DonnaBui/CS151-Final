package echo;

import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerTypeName) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = Class.forName(handlerTypeName);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		while(true) {
			try {
				Socket client = mySocket.accept(); // accept a connection
				RequestHandler handler = makeHandler(client); // make handler
				Thread t = new Thread(handler); // start handler in its own thread
				t.start();
				System.out.println("server address: " + mySocket.getInetAddress());
				if (Server.DEBUG) System.out.println("server listening at port " + myPort);
			} 
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public RequestHandler makeHandler(Socket s) {
		RequestHandler handler = null;
        try {
            handler = (RequestHandler) handlerType.newInstance();
            handler.setSocket(s); // set handler's socket to s
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return handler;
	}

	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.MathHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}