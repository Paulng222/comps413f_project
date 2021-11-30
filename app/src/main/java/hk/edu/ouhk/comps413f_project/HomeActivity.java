package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView textview, textview2;
    SharedPreferences lInfo,UInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textview = findViewById(R.id.textView);
        textview2 = findViewById(R.id.textView2);

        lInfo = getSharedPreferences("SignUp_Share",0);  // get the data from shared file
        String name = lInfo.getString("name","");
        String email  = lInfo.getString("email","");
        int phone = lInfo.getInt("phone",0);
        String pw =  lInfo.getString("password","");
        textview.setText(name);
        textview2.setText(pw);


        UInfo = getSharedPreferences("UInfo_Share",0);
        SharedPreferences.Editor editor  = UInfo.edit();
        editor.putString("name",name);     //
        editor.putString("email",email);   //    Putting the variables to editor.
        editor.putInt("phone",phone);   //
        editor.putString("password",pw);   //
        editor.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sMovie) {
            Intent searchIntent = new Intent(HomeActivity.this, searchActivity.class);
            startActivity(searchIntent);
            finish();                                                                //
        } else if (id == R.id.mFavourite) {
            //Intent mIntent = new Intent(MainActivity.this, earthquakeView.class);
            //startActivity(mIntent);
           // finish();
        } else if (id == R.id.uProfile){
            Intent pIntent = new Intent(HomeActivity.this, UserInfoActivity.class);
            startActivity(pIntent);
            finish();
        } else if (id == R.id.about){
            //Intent aIntent = new Intent(HomeActivity.this, contactFormActivity.class);
            //startActivity(aIntent);
            //finish();
        } else if (id == R.id.logout){
            Intent logoutIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}