package echo;

public class Rater {
    private static final Rater rater = new Rater();
    private double numRatings;
    private double ratingTotal;

    // singleton has private constructor and instance variable
    private Rater() { 
        this.numRatings = 0;
        this.ratingTotal = 0;
    }

    public static Rater getInstance() {
        return rater;
    }

    public synchronized void addRating(double rating) { // add rating to total amt
        numRatings++;
        ratingTotal += rating;
    }

    public synchronized int getRatingCount() { // returns current number of ratings
        return (int) numRatings;
    }

    public synchronized double getAvgRating() { // returns average star rating
        if (numRatings > 0) {
            return ratingTotal/numRatings;
        }
        return 0; // prevents division by 0
    }
}
