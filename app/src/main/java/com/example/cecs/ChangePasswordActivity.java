package com.example.cecs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = ChangePasswordActivity.class.getSimpleName();
    EditText newPasswordET;
    EditText retypePasswordET;
    Button confirmChangeBtn;
    User user;

    // Password regex for password validation
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z0-9])" +       //any letter or number
                    "(?=\\S+$)" +               //no white spaces
                    ".{4,}" +                   //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPasswordET = findViewById(R.id.newPasswordET);
        retypePasswordET = findViewById(R.id.retypeNewPasswordET);
        confirmChangeBtn = findViewById(R.id.confirmChangePasswordBtn);

        Intent accountIntent = getIntent();
        user = (User) accountIntent.getSerializableExtra("userObject");
        Log.e(TAG, "Starting to retrieve user for password change.. ");
        Log.e(TAG, "Current password.. " + user.password);

        confirmChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = newPasswordET.getText().toString();
                String retypePassword = retypePasswordET.getText().toString();

                if(validatePassword( password,  retypePassword)) {
                    // new intent.
                    Intent resultIntent = getIntent();
                    // put newUser into userObject in intent
                    user.password = password;
                    resultIntent.putExtra("userObject", user);
                    // set the result for this activity with the new intent
                    setResult(Activity.RESULT_OK, resultIntent);
                    Toast.makeText(ChangePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "New password gotten: " + user.password);
                }


            }


        });

    }

    /**
     * Validates password's field against 4 requirements. Shows error message if fail.
     * @param password - User's password
     * @param retypePassword - retyped string to check for matching
     * @return boolean - value for validation
     */
    private boolean validatePassword(String password, String retypePassword) {
        if (password.isEmpty()) {
            newPasswordET.setError("Field Cannot Be Empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            newPasswordET.setError("Password Must Contain:\nNo Spaces\nAt Least 4 Characters");
            return false;
        } else if (retypePassword.isEmpty()) {
            retypePasswordET.setError("Field Cannot Be Empty");
            return false;
        } else if (!retypePassword.equals(password)) {
            retypePasswordET.setError("Password Does Not Match");
            return false;
        } else {
            return true;
        }
    }

}
