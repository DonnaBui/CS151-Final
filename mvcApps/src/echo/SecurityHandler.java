package echo;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SecurityHandler extends ProxyHandler {
    private static final Map<String, String> users = new HashMap<>();
    private boolean loggedIn = false;

    public SecurityHandler(Socket s) {
        super(s);
    }
    public SecurityHandler() {
        super();
    }

    // Synchronized methods to ensure thread safety
    private String get(String username) { 
        synchronized(users) {
            return users.get(username);
        }
    }

    private String put(String username, String password) {
        synchronized(users) {
            return users.put(username, password);
        }
    }

    @Override
    protected String response(String request) throws Exception {
        if (Server.DEBUG) System.out.println("SecurityHandler received request: " + request); // debug
        String reply = "";

        if (loggedIn) return super.response(request); // Forward request to peer if logged in
        else if (!loggedIn) { // Handle login/registration if not logged in
            String invalidCmd = "Invalid command. Please register or log in first."
            + " Usage: <new/login> <username> <password>";

            // Make sure request.split() doesn't break
            if (request == null || request.trim().isEmpty()) return invalidCmd;
            
            String[] args = request.split("\\s+");

            // Make sure there's exactly 3 arguments: cmd, username, and password
            if (args.length != 3) return invalidCmd;

            String cmd = args[0].toLowerCase();
            String username = args[1];
            String password = args[2];

            switch (cmd) {
                case "new":
                    // Check if user already exists
                    if (get(username) != null) {
                        reply = "Error: user " + username + " already exists.";
                    } else {
                        // Register new user
                        put(username, password);
                        reply = "User " + username + " registered successfully.";
                    }
                    break;
                case "login":
                    // Check if user exists
                    if (get(username) == null) {
                        reply = "Error: user " + username + " not found.";
                    } // Check if password is correct
                    else if (!get(username).equals(password)) {
                        reply = "Incorrect password entered for " + username;
                    } // Log in if user exists and password is correct
                    else {
                        loggedIn = true;
                        reply = "User " + username + " logged in successfully.";
                    }
                    break;
                default: // Else, prompt the user again to register/log in
                    return invalidCmd;
            } // switch

            // If login fails, terminate session
            if (loggedIn == false) {
                reply += " Terminating session. Please start a new session to continue.";
                shutDown(); 
            }

        }
        return reply; // If not logged in, return the reply as determined above
    }
}