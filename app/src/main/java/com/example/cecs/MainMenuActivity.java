package com.example.cecs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {



    private ImageView tierIV;
    private TextView tierTV;

    private String rankScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tierIV = findViewById(R.id.menuTierIV);
        tierTV = findViewById(R.id.menuTierTV);

        Intent resIntent = getIntent();

        /**
         * get rank result
         */
        if(resIntent.hasExtra("resultRankTier")) {
            tierTV.setText(resIntent.getStringExtra("resultRankTier"));
            if(resIntent.getStringExtra("resultRankTier").equals("Iron Tier")) {
                tierIV.setBackgroundResource(R.drawable.iron);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Bronze Tier")) {
                tierIV.setBackgroundResource(R.drawable.bronze);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Silver Tier")) {
                tierIV.setBackgroundResource(R.drawable.silver);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Gold Tier")) {
                tierIV.setBackgroundResource(R.drawable.gold);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Platinum Tier")) {
                tierIV.setBackgroundResource(R.drawable.platinum);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Diamond Tier")) {
                tierIV.setBackgroundResource(R.drawable.diamond);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Master Tier")) {
                tierIV.setBackgroundResource(R.drawable.master);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Grand Master Tier")) {
                tierIV.setBackgroundResource(R.drawable.grandmaster);
            } else if (resIntent.getStringExtra("resultRankTier").equals("Challenger Tier")) {
                tierIV.setBackgroundResource(R.drawable.challenger);
            }
        }

        Button buttonStartQuiz = findViewById(R.id.menuTakeQuizBtn);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(MainMenuActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
