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
    Button uSave;
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
        uSave = findViewById(R.id.uSave);
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


        uSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editName = uName.getText().toString();
                String editEmail = uEmail.getText().toString();
                int editPhone = Integer.parseInt(uPhone.getText().toString().trim());
                String editPw = uPw.getText().toString();

                signUp = getSharedPreferences("SignUp_Share",0);
                SharedPreferences.Editor editor  = signUp.edit();
                editor.putString("name", editName);     //
                editor.putString("email", editEmail);   //    Putting the variables to editor.
                editor.putInt("phone", editPhone);   //
                editor.putString("password", editPw);   //
                editor.commit();

                Toast.makeText(getApplicationContext(), "User Information is updated.",
                        Toast.LENGTH_SHORT).show();

                Intent SignUpIntent = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(SignUpIntent);
                finish();
            }
        });

    }
}