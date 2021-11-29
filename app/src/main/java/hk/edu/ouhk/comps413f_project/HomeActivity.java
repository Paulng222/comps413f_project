package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView textview, textview2;
    Button logout;
    SharedPreferences lInfo,UInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textview = findViewById(R.id.textView);
        textview2 = findViewById(R.id.textView2);
        logout = findViewById(R.id.logout);

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

        logout.setOnClickListener(new View.OnClickListener() { // logout the account
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });
    }
}