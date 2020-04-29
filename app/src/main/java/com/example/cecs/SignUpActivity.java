package com.example.cecs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * This activity emulates a sign up page and sends back data once validated and finished
 * @author Manuel Beltran, Ben Seo
 */
public class SignUpActivity extends AppCompatActivity {

    // Password regex for password validation
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z0-9])" +       //any letter or number
                    "(?=\\S+$)" +               //no white spaces
                    ".{4,}" +                   //at least 4 characters
                    "$");

    // Phone number regex for phone number validation
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");  // any 10 digit number

    private Button signMeUpButton;
    private EditText etUserName, etPassword, etRetypePassword, etEmail, etPhoneNumber;
    private UserDataDictionary userDict = null;
    private HashMap<String, User> userData = null;

    /**
     onCreate method will initialize the UI in this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userDict = (UserDataDictionary)getIntent().getSerializableExtra("UserDictionary");
        userData = userDict.getUserDict();

        // Get activities UI id
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRetypePassword = findViewById(R.id.etRetypePassword);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        signMeUpButton = findViewById(R.id.open_main_activity);

        signMeUpButton.setOnClickListener(new View.OnClickListener() {

            /**
             onClick method, once button is clicked, get text from editText and make them into strings. Then, validate them.
             If everything is validated, create new User instance with these validated Strings. Then put them in UserData hashmap and set them.
             Setting updates the current hashmap into the new one.
             */
            @Override
            public void onClick(View v) {
                // get text and change them into string.
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String rePassword = etRetypePassword.getText().toString();
                String email = etEmail.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();

                // validate strings
                if (validateUsername(username) && validatePassword(password, rePassword) && validateEmail(email) && validatePhoneNumber(phoneNumber)) {
                    // make new User instance with the validated string
                    User newUser = new User(username, password, email, phoneNumber);

                    // put newUser into hashmap as a value. The key is the username of newUser.
                    userData.put(newUser.username, newUser);
                    // set the updated userData hashmap from the UserDataDictionary class.
                    userDict.setUserDict(userData);

                    // new intent.
                    Intent resultIntent = new Intent();
                    // put newUser into userObject in intent
                    resultIntent.putExtra("userObject", newUser);
                    // set the result for this activity with the new intent
                    setResult(Activity.RESULT_OK, resultIntent);
                    // finish: transfer intent and data to previous activity, while terminating current intent
                    finish();
                }
            }
        });
    }

    /**
     * Validates 'username' information if empty or exits. Sets an error message if not passed.
     * @param username - the name for the user
     * @return boolean - validation against username standards
     */
    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            etUserName.setError("Field Cannot Be Empty");
            return false;
        } else if (userDict.getUserDict().containsKey(username)) {
            etUserName.setError("Username Already Exists");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validates 'email' information to check if empty and in a email format. Sets error
     * message if false.
     * @param email - email of the User
     * @return boolean - value for validation
     */
    private boolean validateEmail(String email) {
        String emailInput = email.trim();
        if (emailInput.isEmpty()) {
            etEmail.setError("Field Cannot Be Empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            etEmail.setError("Please Enter a Valid Email Address");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validates password's field against 4 requirements. Shows error message if fail.
     * @param password - User's password
     * @param retypePassword - retyped string to check for matching
     * @return boolean - value for validation
     */
    private boolean validatePassword(String password, String retypePassword) {
        if (password.isEmpty()) {
            etPassword.setError("Field Cannot Be Empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            etPassword.setError("Password Must Contain:\nNo Spaces\nAt Least 4 Characters");
            return false;
        } else if (retypePassword.isEmpty()) {
            etRetypePassword.setError("Field Cannot Be Empty");
            return false;
        } else if (!retypePassword.equals(password)) {
            etRetypePassword.setError("Password Does Not Match");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validates phoneNumber's field against 2 requirements. Shows error message if fail.
     * Checks if empty and is in a phone number format.
     * @param phoneNumber - User's phoneNumber
     * @return boolean - value for validation
     */
    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            etPhoneNumber.setError("Field Cannot Be Empty");
            return false;
        } else if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            etPhoneNumber.setError("Enter a Valid 10 Digit Phone Number");
            return false;
        } else {
            return true;
        }
    }
}