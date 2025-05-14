package echo;

import java.util.HashMap;

public class SafeTable extends HashMap<String,String> {

    // Singleton instance w/ private constructor and a getInstance() method
    private static final SafeTable table = new SafeTable();

    private SafeTable() {
        super();
    }

    public static SafeTable getInstance() {
        return table;
    }

    // Synchronized methods, thread safe
    @Override
    public synchronized String get(Object request) { 
        return super.get(request);
    }

    @Override
    public synchronized String put(String request, String reply) {
        return super.put(request, reply);
    }

}
