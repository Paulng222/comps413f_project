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
    SharedPreferences lInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textview = findViewById(R.id.textView);
        textview2 = findViewById(R.id.textView2);
        logout = findViewById(R.id.logout);

        lInfo = getSharedPreferences("Login_Share",0);
        String name = lInfo.getString("name","");
        textview.setText(name);
        String pw =  lInfo.getString("password","");
        textview2.setText(pw);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor =  lInfo.edit();
                editor.clear();
                editor.apply();

                Intent logoutIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });
    }
}