package hk.edu.ouhk.comps413f_project;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchActivity extends Activity {
    // EditText for user input
    private View MovieView;
    private EditText searchInput;
    static Movie resultMovie = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);
        searchInput = findViewById(R.id.searchinput);
        MovieView = findViewById(R.id.searchresult);
    }


    public void MovieSearch(View button) {
        // Hide the keyboard when the button is click
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(button.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        resultMovie = null;

        // Get the search string from the EditText
        String queryString = searchInput.getText().toString();

        // If no user input, show a Toast object
        if (queryString.length() == 0) {
            Toast aToast=Toast.makeText(this, "No input!!!", Toast.LENGTH_LONG);
            aToast.show();
        }
        // If the network is not available or not connected, show a Toast object
        else if (networkInfo == null  || !networkInfo.isConnected()) {
            Toast aToast=Toast.makeText(this, "Please check your network connection and try again", Toast.LENGTH_LONG);
            aToast.show();
        } else {
            String searchBy = "title";

            // Add code here
            // Task 2: Create and execute GetBookAsyncTask object
            // i. Create a GetBookAsyncTask object
            // ii. Start it with execute method
            new GetMovieAsyncTask(MovieView, searchBy).execute(queryString);

            TextView titleView = MovieView.findViewById(R.id.dialogtitle);
            titleView.setText("Searching...");
        }
    }


}