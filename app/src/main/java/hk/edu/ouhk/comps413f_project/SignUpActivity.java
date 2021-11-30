package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
   EditText sName, sEmail, sPhone, sPw;
   Button signUpButton;
   SharedPreferences signUpInfo;
   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sName = findViewById(R.id.sName);
        sEmail = findViewById(R.id.sEmail);
        sPhone = findViewById(R.id.sPhone);
        sPw = findViewById(R.id.sPw);
        signUpButton = findViewById(R.id.signUpButton);
        toolbar = findViewById(R.id.toolbar);

        signUpInfo = getSharedPreferences("SignUp_Share", 0);  // Create a  SharedPreferences file.

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String signName = sName.getText().toString();
               String signEmail = sEmail.getText().toString();
               int signPhone = Integer.parseInt(sPhone.getText().toString().trim());
               String signPassword = sPw.getText().toString();

                SharedPreferences.Editor editor  = signUpInfo.edit();
                editor.putString("name",signName);     //
                editor.putString("email",signEmail);   //    Putting the variables to editor.
                editor.putInt("phone",signPhone);   //
                editor.putString("password",signPassword);   //
                editor.commit();

                Toast.makeText(getApplicationContext(), "Sign Up Successfully",
                        Toast.LENGTH_SHORT).show();

                Intent SignUpIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(SignUpIntent);
                finish();
            }
        });


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(splashIntent);
                finish();
            }
        });
    }
}