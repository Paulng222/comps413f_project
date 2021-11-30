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
    TextView dateView;
    TextView sOverView;

    private String searchBy;


    // Initialize reference to the movieView in searchActivity.
    public GetMovieAsyncTask(View MovieView, String searchBy) {
        this.MovieView = MovieView;
        titleView = MovieView.findViewById(R.id.dialogtitle);
        dateView = MovieView.findViewById(R.id.date);
        sOverView = MovieView.findViewById(R.id.overview);

        this.searchBy = searchBy;
    }

    // Make query with themoviedb api method getMovieInfo of the MovieApiUtil class
    // return null when movie cannot be found
    // Otherwise, return movie object
    @Override
    protected Movie doInBackground(String... strings) {
        String resultMovieStr;
        Movie resultMovie = null;


        //  movie search
        //  Get movie result in JSON format and stored in a string with method getMovieInfo
        //  Create movie object with the resulted JSON string
        resultMovieStr = MovieApiUtil.getMovieInfo(strings[0]);
        resultMovie = retrieveMovieDetails(resultMovieStr);

        return resultMovie;
    }

    // Handle results after query is made
    @Override
    protected void onPostExecute(Movie resultMovie) {
        // the failed result is displayed if no result found
        if (resultMovie == null) {
            updateUi(NO_RESULT, "", "");
            return;
        }


        // Show resulted movie details using updateUi method
        updateUi(resultMovie.getTitle(), resultMovie.getOverview(), resultMovie.getReleasedate());

        searchActivity.resultMovie = resultMovie;
    }

    // Update ui in searchActivity
    public void updateUi(String title, String overview, String releasedate) {
        titleView.setText(title);
        dateView.setText(releasedate);
        sOverView.setText(overview);
    }

    // Get movie details from JSON string
    public Movie retrieveMovieDetails(String result) {
        Movie resultedMovie = null;

        // If result found
        try {

            JSONObject jsonObject = new JSONObject(result);  // Convert the result string into a JSON object

            JSONArray resultsArray = jsonObject.getJSONArray("results"); // Get the JSONArray of "items"


            int i = 0;
            String title = null;
            String overview = null;
            String releasedate = null;

            // Search movie from result JSONArray object
            while (i < resultsArray.length() && (title == null || overview == null || releasedate == null)) {

                JSONObject movie = resultsArray.getJSONObject(i);  // Get the current movie
                // Get information of the current movie


                // Get the title, release date and overview from the current movie
                // handle the Catch exception if any of the field is empty and try the next movie record
                try {

                    title = movie.getString("original_title");
                    overview = movie.getString("overview");
                    releasedate = movie.getString("release_date");

                    // If all the fields are found, create a new movie object
                    if (title != null && overview != null && releasedate!= null) {
                        resultedMovie = new Movie(title, overview, releasedate);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Move to the next movie record
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultedMovie;
    }



}