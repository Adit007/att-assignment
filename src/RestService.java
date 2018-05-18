import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestService {

    /**
     * Constructor that accepts URL input.
     * 
     * @param url
     */
    public RestService(final String url) {
        try {
            consumeGetRequest(url);
        } catch (ConnectionFailedException | IOException e) {
            // this can also be logged into a file at ERROR level.
            System.out.println("Could not process the GET request due to some internal error..." + e.getMessage());
        }
    }

    /**
     * Consumer that handles the Http Get Request
     *
     * @param url
     * @throws ConnectionFailedException
     * @throws IOException
     */
    private void consumeGetRequest(final String url) throws ConnectionFailedException, IOException {
        HttpURLConnection conn;
        conn = HttpConnection.doGetWithJson(url);
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new ConnectionFailedException("Failed to open Http Connection ", conn.getResponseCode());
        }

        final String response = parseResponse(conn);
        processResponse(response);
    }

    /**
     * Parse the response into a json string.
     *
     * @param conn
     * @return
     * @throws IOException
     */
    private String parseResponse(final HttpURLConnection conn) throws IOException {
        final StringBuilder strBuilder = new StringBuilder();
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.DEFAULT_ENCODING));
        String output;
        while ((output = br.readLine()) != null) {
            strBuilder.append(output);
        }
        return strBuilder.toString();
    }

    /**
     * Process the response as per the rules. Rule 1: Print all the keys in each json document. Rule 2: Print sum of all
     * the numbers in each json document. Rule 3: Print the running sum of all the numbers in the list of json
     * documents.
     *
     * @param response
     */
    private void processResponse(final String response) {
        final Gson gson = new Gson();
        final List<Map<Object, Object>> jsonDocumentArr = gson.fromJson(response, ArrayList.class);
        double runningTotalOfAllNumbers = 0;
        for (final Map<Object, Object> item : jsonDocumentArr) {
            // print all the keys of each json document present in the response array
            System.out.println("Keys in the current json document " + item.keySet() + "\n");
            // print the sum of numbers
            final List<Number> numbers = (List<Number>) item.get(Constants.NUM_KEY);
            final double sumOfNumsInCurrentJsonDoc = numbers.stream().mapToDouble(number -> number.doubleValue()).sum();
            System.out.println("Sum of all the numbers in the current json document" + sumOfNumsInCurrentJsonDoc);
            // print the running sum of the numbers for the json documents parsed so far.
            runningTotalOfAllNumbers += sumOfNumsInCurrentJsonDoc;
            System.out.println(
                "Running sum of numbers from all json documents processed so far " + runningTotalOfAllNumbers + "\n");
        }
        // print the final total sum
        System.out.println("Total sum of all the numbers from all json documents" + runningTotalOfAllNumbers + "\n");
    }
}
