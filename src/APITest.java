/**
 *
 * This is an entry point class to accept the URL as input and process the request.
 *
 */
public class APITest {
    public static void main(final String[] args) {
        if (args[0] != null) {
            new RestService(args[0]);
        } else {
            System.err.println("Please provide the URL input");
        }
    }
}
