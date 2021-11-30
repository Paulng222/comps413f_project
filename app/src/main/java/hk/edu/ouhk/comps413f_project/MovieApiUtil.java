package hk.edu.ouhk.comps413f_project;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieApiUtil {
    private static final String Movie_base_URL = "https://api.themoviedb.org/3/search/movie?";
    //Api Key
    private static final String ApiKey = "api_key";
    // Parameter for the query string
    private static final String QUERY_PARAM = "query";

    static String getMovieInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String MovieJSONString = null;

        try {
            String apikey= "434268f1109ee7536555312c2dd3a8d9";

            // Setup the full query URI
            Uri builtURI = Uri.parse(Movie_base_URL).buildUpon()
                    .appendQueryParameter(ApiKey, apikey)  // ApiKey
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .build();

            // Convert the URI to a URL
            URL requestURL = new URL(builtURI.toString());
            System.out.println(builtURI.toString());
            // Open the network connection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));  //拎結果

            // Use a StringBuilder to store the incoming response
            StringBuilder builder = new StringBuilder(); //store the result

            String line;
            while ((line = reader.readLine()) != null) {
                // Add the current line to the string
                builder.append(line);

                // Adding a newline to the string (optional, not necessary_
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // If empty string, return null
                return null;
            }

            MovieJSONString = builder.toString();    //result to string
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the connection and the buffered reader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return MovieJSONString;
    }
}
