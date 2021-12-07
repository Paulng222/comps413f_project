package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView greeting,hTitle;
    ImageView aniImage;
    SharedPreferences lInfo,UInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        greeting = findViewById(R.id.greeting);
        hTitle = findViewById(R.id.hTitle);
        aniImage = findViewById(R.id.aniImage);

        lInfo = getSharedPreferences("SignUp_Share",0);  // get the data from shared file
        String name = lInfo.getString("name","");
        String email  = lInfo.getString("email","");
        int phone = lInfo.getInt("phone",0);
        String pw =  lInfo.getString("password","");
        greeting.setText("Welcome! " + name);


        UInfo = getSharedPreferences("UInfo_Share",0);
        SharedPreferences.Editor editor  = UInfo.edit();
        editor.putString("name",name);     //
        editor.putString("email",email);   //    Putting the variables to editor.
        editor.putInt("phone",phone);   //
        editor.putString("password",pw);   //
        editor.commit();


        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY",0.0F,360.0F);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",0.0F,0,1F);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",0.0F,0,1F);
        ObjectAnimator.ofPropertyValuesHolder(aniImage,rotationY,scaleX,scaleY).setDuration(3000).start();
        ObjectAnimator.ofPropertyValuesHolder(hTitle,rotationY,scaleX,scaleY).setDuration(3000).start();

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
            Intent mIntent = new Intent(HomeActivity.this, FavouriteActivity.class);
            startActivity(mIntent);
            finish();
        } else if (id == R.id.uProfile){
            Intent pIntent = new Intent(HomeActivity.this, UserInfoActivity.class);
            startActivity(pIntent);
            finish();
        } else if (id == R.id.about){
            Intent aIntent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(aIntent);
            finish();
        } else if (id == R.id.logout){
            SharedPreferences.Editor editor  =lInfo.edit();
            editor.clear().apply();

            Intent logoutIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}