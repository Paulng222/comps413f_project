package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText logName, logPw;
    Button login, register;
    SharedPreferences loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logName = findViewById(R.id.logName);
        logPw = findViewById(R.id.logPw);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        loginInfo = getSharedPreferences("Login_Share", 0);  // Create a  SharedPreferences file.

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = logName.getText().toString();
                String pw = logPw.getText().toString();

                SharedPreferences.Editor editor =  loginInfo.edit();
                editor.putString("name",name);             //
                editor.putString("password",pw);           //  put related information to shared file
                editor.apply();

                if(!logName.equals("") && !logPw.equals("")){
                    SharedPreferences userInfo = getSharedPreferences("Login_Share",0);
                    String lName = userInfo.getString("name",name);
                    String  lPw =  userInfo.getString("password",pw);

                    if(name == lName && pw == lPw){
                        Intent loginIntent = new Intent(LoginActivity.this, UserInfoActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Name or Password is incorrect.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                
            }
        });
    }
}