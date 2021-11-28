package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity {
    EditText uName;
    Toolbar toolbar;
    SharedPreferences UserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        uName = findViewById(R.id.uName);
        toolbar = findViewById(R.id.toolbar);

        UserInfo = getSharedPreferences("Login_Share",0);
        String name = UserInfo.getString("name","");
        uName.setText(name);

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