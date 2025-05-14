package echo;

import java.net.Socket;

public class CacheHandler extends ProxyHandler {

    // Singleton instance of SafeTable
    private final SafeTable cache = SafeTable.getInstance();

    public CacheHandler(Socket s) {
        super(s);
    }
    public CacheHandler() {
        super();
    }

    @Override
    protected String response(String request) throws Exception {
        if (Server.DEBUG) System.out.println("CacheHandler received request: " + request); // debug
        
        String cached = cache.get(request); // Check if request is in the cache
        if (cached != null) {
            if (Server.DEBUG) System.out.println("Found in cache: " + cached); 
            return cached; // Return cached response
        } 
        else {
            if (Server.DEBUG) System.out.println("Not found in cache, forwarding request to peer");
            String reply = super.response(request); // Use method from ProxyHandler to forward request to peer
            
            // Cache reply before forwarding to client
            if (Server.DEBUG) System.out.println("Caching response: " + reply);
            cache.put(request, reply);

            return reply;
        }
    }
}
