import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is to open Http Connections for GET/POST/DELETE etc. For now only get has been implemented. It can be
 * extended further based on the use case.
 *
 */
public class HttpConnection {

    private HttpConnection() {}

    protected static HttpURLConnection doGetWithJson(final String url) throws IOException {
        final URL restUrl = new URL(url);
        final HttpURLConnection conn = (HttpURLConnection) restUrl.openConnection();
        conn.setRequestMethod(Constants.GET);
        conn.setRequestProperty(Constants.ACCEPT_HEADER, Constants.MEDIA_TYPE);
        conn.setRequestProperty(Constants.CONTENT_TYPE, Constants.MEDIA_TYPE);
        return conn;
    }
}
