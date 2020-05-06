package com.example.cecs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText newPasswordET;
    EditText retypePasswordET;
    Button confirmChangeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPasswordET = findViewById(R.id.newPasswordET);
        retypePasswordET = findViewById(R.id.retypeNewPasswordET);
        confirmChangeBtn = findViewById(R.id.confirmChangePasswordBtn);

        confirmChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
