package CS151final.problem3;

import java.net.Socket;
import echo.*;

public class RatingHandler extends ProxyHandler {

    private boolean hasRated = false;
    private final Rater rater = Rater.getInstance();

    public RatingHandler(Socket s) {
        super(s);
    }

    public RatingHandler() {
        super();
    }

    @Override
    protected String response(String request) throws Exception {

         // Make sure request.split() doesn't break
        if (request == null || request.trim().isEmpty()) return "Invalid Command.";

        String[] args = request.split("\\s+");
        String operation = args[0].toLowerCase();

        if (operation.equals("rate")) {
            double rating;
            try {  
                rating = Double.valueOf(args[1]); 
            } catch (NumberFormatException e) {
                return "Invalid rating. Please provide a number from 0 to 5.";
            }
            if (hasRated == true) {
                return "you already rated this service";
            }
            else if (args.length != 2) {
                return "Invalid command. Proper usage: rate [number from 0 - 5]";
            }
            else if (rating < 0 || rating > 5) {
                return "Rating out of range";
            }
            else {
                rater.addRating(rating);
                hasRated = true;
                return "Thank you";
            }
        }
        if (operation.equals("rating")) {
            return "rating = " + rater.getAvgRating() + " #ratings = " + rater.getRatingCount();
        }

    return super.response(request); // forward other requests if not rating-related
    }
}


