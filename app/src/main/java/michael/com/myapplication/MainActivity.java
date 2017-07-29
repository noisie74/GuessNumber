package michael.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView rewards;
    private Button add, multi;
    private int rewardsPoints;
    private int addCode = 0;
    private int multiCode = 1;
    public final String KEY = "Key";
    public final String POINTS = "Points";
    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setIntent();

        setClickListener();
    }

    private void initView() {
        rewards = (TextView) findViewById(R.id.rewards);
        add = (Button) findViewById(R.id.buttonAdd);
        multi = (Button) findViewById(R.id.buttonMult);
    }


    private void setClickListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(KEY, addCode);
                startActivity(intent);
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(KEY, multiCode);
                startActivity(intent);
            }
        });
    }

    private void getRewards() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        rewardsPoints = sharedPreferences.getInt(POINTS, 0);
    }

    private void setIntent() {
        intent = new Intent(MainActivity.this, GuessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRewards();
        rewards.setText("Your score: " + String.valueOf(rewardsPoints));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().putInt(POINTS, 0).apply();
    }
}
