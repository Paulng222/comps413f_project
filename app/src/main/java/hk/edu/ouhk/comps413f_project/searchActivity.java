package hk.edu.ouhk.comps413f_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Date;

public class searchActivity extends Activity {

    private View MovieView;
    private EditText searchInput;
    static Movie resultMovie = null;
    Toolbar toolbar;
    Button addbtn;
    Cursor cursor = null;
    SQLiteDatabase db;
    String sql;
    String dataStr;
    SharedPreferences favInfo;
    Date cDate = new Date(System.currentTimeMillis());
    long millis = cDate.getTime();

    TextView title,date,overview,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);
        searchInput = findViewById(R.id.searchinput);
        MovieView = findViewById(R.id.searchresult);
        addbtn = findViewById(R.id.addbtn);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.dialogtitle);
        date = findViewById(R.id.date);
        overview = findViewById(R.id.overview);
        text = findViewById(R.id.text);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(searchActivity.this, HomeActivity.class);
                startActivity(searchIntent);
                finish();
            }
        });

        favInfo = getSharedPreferences("Fav_Share", 0);  // Create a  SharedPreferences file.

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dTitle = resultMovie.getTitle();
                String dDate = resultMovie.getReleasedate();
                String dOverview = resultMovie.getOverview();

                SharedPreferences.Editor editor  = favInfo.edit();
                editor.putString("title",dTitle);     //
                editor.putString("date",dDate);   //    Putting the variables to editor.
                editor.putString("overview",dOverview);
                editor.putLong("addTime",millis);   //
                editor.commit();

                Toast.makeText(getApplicationContext(), "Added to Favourite Successfully",
                        Toast.LENGTH_SHORT).show();

            }
        });


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

        String queryString = searchInput.getText().toString();

        // show a Toast object if user has not input something
        if (queryString.length() == 0) {
            Toast aToast=Toast.makeText(this, "Search field is empty.", Toast.LENGTH_LONG);
            aToast.show();
        }
        //show a Toast object if the network not connected
        else if (networkInfo == null  || !networkInfo.isConnected()) {
            Toast aToast=Toast.makeText(this, "Please check your network connection and try again", Toast.LENGTH_LONG);
            aToast.show();
        } else {
            String searchBy = "title";


            //  Create and execute GetMovieAsyncTask object
            new GetMovieAsyncTask(MovieView, searchBy).execute(queryString);

            TextView titleView = MovieView.findViewById(R.id.dialogtitle);
            titleView.setText("Searching...");
        }
    }


}