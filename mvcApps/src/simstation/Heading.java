package simstation;

import mvc.Utilities;

public enum Heading {
    NORTH, SOUTH, EAST, WEST;

    public static Heading parse(String heading) {
        if(heading.equalsIgnoreCase("north")) return NORTH;
        if(heading.equalsIgnoreCase("east")) return EAST;
        if(heading.equalsIgnoreCase("west")) return WEST;
        if(heading.equalsIgnoreCase("south")) return SOUTH;
        Utilities.error("Invalid heading: " + heading);
        return null;
    }

    public static Heading random() {
        int luck = Utilities.rng.nextInt(4);
        if (luck == 0) return NORTH;
        if (luck == 1) return SOUTH;
        if (luck == 2) return EAST;
        return WEST;
    }
    // Not needed
    public Heading getHeading() {
        return random();
    }
}
