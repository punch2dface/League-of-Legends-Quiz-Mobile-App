package com.example.cecs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Result Activity
 * This activity will show the number of questions right, the image and text of the rank,
 * and a button that takes you back to the Main menu activity.
 */
public class ResultActivity extends AppCompatActivity {

    private String TAG = ResultActivity.class.getSimpleName();

    private TextView resultScoreTV;
    private ImageView resultRankIV;
    private TextView resultTierTV;
    private Button resultExitBtn;

    private int questionCountTotal;
    private int questionCorrectCount;
    private float quizScore;

    /**
     * onCreate method
     * get UI components for this activity,
     * get the score and the total number of questions that has been passed from the Quiz Activity
     * get the percentage from the score / total questions
     * Set the text of the resultScoreTV to the percentage
     * if the percentage fall in a certain interval, set the ResultTierTV and the resultRankIV to the appropriate image and text.
     * The Exit button has an onClickListener where when clicked, put the tier into the intent and finish the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultScoreTV = findViewById(R.id.resultScore);
        resultRankIV = findViewById(R.id.resultRank);
        resultTierTV = findViewById(R.id.resultTierTV);
        resultExitBtn = findViewById(R.id.exitBtn);

        Intent intent = getIntent();
        questionCorrectCount = intent.getIntExtra("extraScore", 0);
        Log.e(TAG, " Correct Count = " + questionCorrectCount);
        questionCountTotal = intent.getIntExtra("totalQuestion", 0);
        Log.e(TAG, "Total Count = " + questionCountTotal);

        quizScore = ((float) questionCorrectCount / (float) questionCountTotal) * 100;
        Log.e(TAG, "Quiz Score = " + quizScore);

        resultScoreTV.setText(questionCorrectCount + "/" + questionCountTotal + "\nCorrect");


        if(quizScore <= 11.11){
            //iron
            resultRankIV.setBackgroundResource(R.drawable.iron);
            resultTierTV.setText("Iron Tier");
        } else if (quizScore <= 22.22) {
            //bronze
            resultRankIV.setBackgroundResource(R.drawable.bronze);
            resultTierTV.setText("Bronze Tier");
        } else if (quizScore <= 33.33) {
            //silver
            resultRankIV.setBackgroundResource(R.drawable.silver);
            resultTierTV.setText("Silver Tier");
        } else if (quizScore <= 44.44) {
            //gold
            resultRankIV.setBackgroundResource(R.drawable.gold);
            resultTierTV.setText("Gold Tier");
        } else if (quizScore <= 55.55) {
            //plat
            resultRankIV.setBackgroundResource(R.drawable.platinum);
            resultTierTV.setText("Platinum Tier");
        } else if (quizScore <= 66.66) {
            //diamond
            resultRankIV.setBackgroundResource(R.drawable.diamond);
            resultTierTV.setText("Diamond Tier");
        } else if (quizScore <= 77.77) {
            //master
            resultRankIV.setBackgroundResource(R.drawable.master);
            resultTierTV.setText("Master Tier");
        } else if (quizScore <= 88.88) {
            //grandmaster
            resultRankIV.setBackgroundResource(R.drawable.grandmaster);
            resultTierTV.setText("Grand Master Tier");
        } else if (quizScore <= 100) {
            //challenger
            resultRankIV.setBackgroundResource(R.drawable.challenger);
            resultTierTV.setText("Challenger Tier");
        }

        resultExitBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * put the tier into intent and set the result for the intent.
             * then, finish the activity
             * @param v
             */
            @Override
            public void onClick(View v) {

                String tier = resultTierTV.getText().toString();
                Log.e(TAG, "Tier String: " + tier);
                getIntent().putExtra("resultRankTier", tier);
                setResult(Activity.RESULT_OK, getIntent());
                finish();
            }
        });
    }
}
