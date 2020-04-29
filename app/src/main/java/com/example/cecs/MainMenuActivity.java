package com.example.cecs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button buttonStartQuiz = findViewById(R.id.menuTakeQuizBtn);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startQuiz();
            }
        });
    }

    private void startQuiz(){
        Intent intent = new Intent(MainMenuActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
