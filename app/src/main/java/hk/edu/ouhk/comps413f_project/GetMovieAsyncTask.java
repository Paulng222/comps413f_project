package hk.edu.ouhk.comps413f_project;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetMovieAsyncTask extends AsyncTask<String, Void, Movie> {

    private static final String NO_RESULT = "No Result Found!!!";

    private View MovieView;
    TextView titleView;
    TextView authorsView;
    TextView isbn10View;

    private String searchBy;

    // Constructor
    // Initialize reference to the bookview in BookSearchActivity.
    public GetMovieAsyncTask(View MovieView, String searchBy) {
        this.MovieView = MovieView;
        titleView = MovieView.findViewById(R.id.dialogtitle);
        authorsView = MovieView.findViewById(R.id.dialogauthor);
        isbn10View = MovieView.findViewById(R.id.dialogisbn10);

        this.searchBy = searchBy;
    }

    // Make query with Google Books api method getBookInfo of the BookApiUtil class
    // If book cannot be found, null is returned
    // Otherwise, a book object is return
    @Override
    protected Movie doInBackground(String... strings) {
        String resultMovieStr;
        Movie resultMovie = null;

        // Add code here
        // Task 1: movie search
        // i. Get book result in JSON format and stored in a string with method getBookInfo
        // ii. Create book object with the resulted JSON string
        resultMovieStr = MovieApiUtil.getMovieInfo(strings[0]);
        resultMovie = retrieveBookDetails(resultMovieStr);

        return resultMovie;
    }

    // Handle results after query is made
    @Override
    protected void onPostExecute(Movie resultMovie) {
        // If no result found, display the failed result
        if (resultMovie == null) {
            updateUi(NO_RESULT, "", "");
            return;
        }

        // Add code here
        // Task 2: Show resulted book details with updateUi method
        updateUi(resultMovie.getTitle(), resultMovie.getOverview(), resultMovie.getReleasedate());

        searchActivity.resultMovie = resultMovie;
    }

    // Update ui control in BookSearchActivity
    public void updateUi(String title, String overview, String releasedate) {
        titleView.setText(title);
        authorsView.setText(releasedate);
        isbn10View.setText(overview);
    }

    // Get book details from JSON string
    public Movie retrieveBookDetails(String result) {
        Movie resultedMovie = null;

        // If result found
        try {
            // Convert the result string into a JSON object
            JSONObject jsonObject = new JSONObject(result);
            // Get the JSONArray of "items"
            JSONArray resultsArray = jsonObject.getJSONArray("results");

            // Declare result variables
            int i = 0;
            String title = null;
            String overview = null;
            String releasedate = null;

            // Search book from result JSONArray object
            while (i < resultsArray.length() && (title == null || overview == null || releasedate == null)) {
                // Get the current book
                JSONObject movie = resultsArray.getJSONObject(i);
                // Get information of the current movie


                // Get the title, authors and isbn from the current book
                // Catch exception if any of the field is empty and try the next record (book)
                try {
                    // Get the title
                    title = movie.getString("original_title");

                    // Get the overview
                    overview = movie.getString("overview");
                    // Get the release_date
                    releasedate = movie.getString("release_date");


                    // If all the fields are found, create a new book object
                    if (title != null && overview != null && releasedate!= null) {
                        resultedMovie = new Movie(title, overview, releasedate);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Move to the next record (book)
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultedMovie;
    }



}