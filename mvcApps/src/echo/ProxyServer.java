package echo;

import java.net.Socket;

public class ProxyServer extends Server {

	String peerHost;
	int peerPort;

	public ProxyServer(int myPort, String service, int peerPort, String peerHost) {
		super(myPort,service);
		this.peerHost = peerHost;
		this.peerPort = peerPort;
	}

    // make a proxy handler and call initPeer
    @Override
	public RequestHandler makeHandler(Socket s) {
        RequestHandler handler = super.makeHandler(s);
        ProxyHandler proxyHandler = (ProxyHandler) handler;
        proxyHandler.initPeer(peerHost, peerPort);
        return proxyHandler;
	}

	public static void main(String[] args) {
		int port = 6666;
		int peerPort = 5555;
		String peerHost = "localhost";
		String service = "echo.RatingHandler";

		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			peerPort = Integer.parseInt(args[1]);
		}
		if (3 <= args.length) {
			port = Integer.parseInt(args[2]);
		}
		if (4 <= args.length) {
			peerHost = args[3];
		}
		Server server = new ProxyServer(port, service, peerPort, peerHost);
		server.listen();
	}
}