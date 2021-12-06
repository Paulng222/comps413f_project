package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    EditText uName, uPw, uEmail, uPhone;
    Toolbar toolbar;
    SharedPreferences UserInfo,signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        uName = findViewById(R.id.uName);
        uEmail = findViewById(R.id.uEmail);
        uPhone = findViewById(R.id.uPhone);
        uPw = findViewById(R.id.uPw);
        toolbar = findViewById(R.id.toolbar);

        UserInfo = getSharedPreferences("UInfo_Share",0);

        String userName = UserInfo.getString("name","");
        String userEmail = UserInfo.getString("email","");
        int userPhone = UserInfo.getInt("phone",0);
        String userPassword = UserInfo.getString("password","");
        uName.setText(userName);
        uEmail.setText(userEmail);
        uPhone.setText("" + userPhone);
        uPw.setText(userPassword);



        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashIntent = new Intent(UserInfoActivity.this, HomeActivity.class);
                startActivity(splashIntent);
                finish();
            }
        });


    }
}