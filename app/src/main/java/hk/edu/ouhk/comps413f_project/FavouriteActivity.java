package hk.edu.ouhk.comps413f_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class FavouriteActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView fText,fText2,fText3,fText4;
    SharedPreferences mInfo;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        toolbar = findViewById(R.id.toolbar);
        fText = findViewById(R.id.fText);
        fText2 = findViewById(R.id.fText2);
        fText3 = findViewById(R.id.fText3);
        fText4 = findViewById(R.id.fText4);
        clearButton = findViewById(R.id.clearButton);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fIntent = new Intent(FavouriteActivity.this, HomeActivity.class);
                startActivity(fIntent);
                finish();
            }
        });

        mInfo = getSharedPreferences("Fav_Share",0);  // get the data from shared file

        String title = mInfo.getString("title","");
        String date  = mInfo.getString("date","");
        String overview =  mInfo.getString("overview","");
        Date mDate = new Date(mInfo.getLong("addTime", 0));
        fText.setText(title);
        fText2.setText(date);
        fText3.setText(overview);
        fText4.setText("" + mDate);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor  = mInfo.edit();
                editor.clear().apply();

                fText.setText("");
                fText2.setText("");
                fText3.setText("");
                fText4.setText("");

                Toast.makeText(getApplicationContext(), "All Favourites are removed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}