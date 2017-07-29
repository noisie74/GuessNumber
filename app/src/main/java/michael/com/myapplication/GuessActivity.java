package michael.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GuessActivity extends AppCompatActivity {

    private TextView rewards;
    private Button guess1, guess2, guess3, guess4, givenNumbers;
    private int intentCode;
    private int rewardsPoints;
    private int number1 = getRandomNumber();
    private int number2 = getRandomNumber();
    private int correctAnswerAdd = number1 + number2;
    private int correctAnswerMult = number1 * number2;
    public final String KEY = "Key";
    public final String POINTS = "Points";
    private View parentLayout;
    SharedPreferences sharedPreferences;
    Intent backToMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        parentLayout = findViewById(android.R.id.content);

        initSharedPref();
        initButtons();
        setView();

        Intent fromMainActivity = getIntent();
        intentCode = fromMainActivity.getIntExtra(KEY, -1);

        setRandText(guess1);
        setRandText(guess2);
        setRandText(guess3);
        setRandText(guess4);
        setCorrectAnswer();
        setWrongAnswer();
    }


    private int getRandomNumber() {
        return (int) (Math.random() * 100);
    }

    private void setRandText(Button button) {
        button.setText(String.valueOf(getRandomNumber()));
    }


    private void setCorrectAnswer() {

        if (intentCode == 0) {

            givenNumbers.setText(String.valueOf(number1) + " " + "+ " + String.valueOf(number2) + " =");
            guess2.setText(String.valueOf(correctAnswerAdd));
            guessResultClick(guess2, true);
        }

        if (intentCode == 1) {
            givenNumbers.setText(String.valueOf(number1) + " " + "* " + String.valueOf(number2) + " =");
            guess3.setText(String.valueOf(correctAnswerMult));
            guessResultClick(guess3, true);
        }
    }

    private void setWrongAnswer() {
        if (intentCode == 0) {
            guessResultClick(guess1, false);
            guessResultClick(guess3, false);
            guessResultClick(guess4, false);
        } else {
            guessResultClick(guess1, false);
            guessResultClick(guess2, false);
            guessResultClick(guess4, false);
        }
    }

    private void initButtons() {

        View id1 = findViewById(R.id.button1);
        View id2 = findViewById(R.id.button2);
        View id3 = findViewById(R.id.button3);
        View id4 = findViewById(R.id.button4);

        switch (rewardsPoints) {

            case 10:
                setButtonId(id1, id2, id3, id4);
                break;
            case 20:
                setButtonId(id2, id3, id4, id1);
                break;
            case 30:
                setButtonId(id3, id4, id1, id2);
            default:
                setButtonId(id4, id1, id2, id3);
        }

    }

    private void setView() {
        rewards = (TextView) findViewById(R.id.textView);
        givenNumbers = (Button) findViewById(R.id.button5);
        rewards.setText("Rewards: " + String.valueOf(rewardsPoints));
    }

    private void setButtonId(View id1, View id2, View id3, View id4) {
        guess1 = (Button) id1;
        guess2 = (Button) id2;
        guess3 = (Button) id3;
        guess4 = (Button) id4;
    }

    private void guessResultClick(Button button, final boolean isCorrect) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isCorrect) {
                    rewards.setText("Rewards: " + String.valueOf(calcRewards()));
                    showMessage("Your answer is correct!");
                    saveRewards(POINTS, rewardsPoints);
                    gameOver();
                    closeActivity();
                } else {
                    showMessage("Your answer is wrong!");
                }

            }
        });
    }

    private int calcRewards() {
        return rewardsPoints += 10;
    }

    private void showMessage(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    private void initSharedPref() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        rewardsPoints = sharedPreferences.getInt(POINTS, 0);
    }

    private void saveRewards(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private void gameOver() {
        if (rewardsPoints == 50) {
            sharedPreferences.edit().putInt(POINTS, 0).apply();
            showMessage("You won! Game over!");
        }
    }

    private void closeActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backToMainActivity =
                        new Intent(GuessActivity.this, MainActivity.class);
                finish();
            }
        }, 3000);

    }

}
