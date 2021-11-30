package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
   TextView aDesc;
   Toolbar aToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aDesc = findViewById(R.id.aDesc);
        aToolbar = findViewById(R.id.aToolbar);
        aDesc.setText("Global Movie is an application that provides movie search service to user.");

        aToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        aToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashIntent = new Intent(AboutActivity.this, HomeActivity.class);
                startActivity(splashIntent);
                finish();
            }
        });
    }


}