package com.example.cecs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class ResultActivity extends AppCompatActivity {

    private String TAG = ResultActivity.class.getSimpleName();

    private TextView resultScoreTV;
    private ImageView resultRankIV;
    private TextView resultTierTV;
    private Button resultExitBtn;

    private int questionCountTotal;
    private int questionCorrectCount;
    private float quizScore;

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
            @Override
            public void onClick(View v) {
                /*
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.id.resultRank);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArr = stream.toByteArray();
                Intent resIntent = new Intent(ResultActivity.this, MainMenuActivity.class);
                resIntent.putExtra("RankPicture", byteArr);
                startActivity(resIntent);
                */

                String tier = resultTierTV.getText().toString();
                Log.e(TAG, "Tier String: " + tier);
                Intent resIntent = new Intent(ResultActivity.this, MainMenuActivity.class);
                resIntent.putExtra("resultRankTier", tier);
                startActivity(resIntent);
            }
        });
    }
}
