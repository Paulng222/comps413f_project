package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText logName, logPw;
    Button login, lSignUp;
    SharedPreferences userInfo;
    TextView textview4, textview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logName = findViewById(R.id.logName);
        logPw = findViewById(R.id.logPw);
        login = findViewById(R.id.login);
        lSignUp = findViewById(R.id.lSignUp);
        textview4 = findViewById(R.id.textView4);
        textview5 = findViewById(R.id.textView5);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = logName.getText().toString().trim();
                String pw = logPw.getText().toString().trim();

                userInfo = getSharedPreferences("SignUp_Share",0);
                String lName = userInfo.getString("name", ""); //    get the name and password in shared file
                String  lPw =  userInfo.getString("password", ""); //
                textview4.setText(lName);
                textview5.setText(lPw);


                    if(name.equals(lName) && pw.equals(lPw)){
                        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(loginIntent);
                        finish();
                    } else if (TextUtils.isEmpty(name) && TextUtils.isEmpty(pw)){
                        Toast.makeText(getApplicationContext(), "Please enter name and password.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Name or Password is incorrect.",
                                Toast.LENGTH_SHORT).show();
                    }

            }
        });

        lSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(SignUpIntent);
                finish();
            }
        });
    }

}