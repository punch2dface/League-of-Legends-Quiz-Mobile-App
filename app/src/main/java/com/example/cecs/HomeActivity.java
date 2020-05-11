package com.example.cecs;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

/**
 * This activity represents a login
 */
public class HomeActivity extends AppCompatActivity {
    private String TAG = HomeActivity.class.getSimpleName();
    Button btnLogin, btnSignUp;
    EditText etUsername, etPassword;
    private UserDataDictionary userDict = new UserDataDictionary();
    private HashMap<String, User> userData = userDict.getUserDict();

    private final int UPDATE_USER_RESULTFLAG = 2;

    /**
     *  onCreate method gets UI from activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // get EditText username and password
        etUsername = findViewById(R.id.homeUsernameET);
        etPassword = findViewById(R.id.homePasswordET);

        // get Button sign up and login
        btnSignUp = findViewById(R.id.homeCreateAccountBtn);
        btnLogin = findViewById(R.id.homeLoginBtn);


        User userAdmin = new User("admin","password", "admin@gmail.com", "345395418");
        addUser(userAdmin);

        /**
         *  btnLogin onClickListener for the Login Button.
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {

            /**
             *  onClick method, when login button is clicked, get and change username and password text to string.
             *  Then, validate them. Once validated, make new Intent instance and putExtra username in "username".
             *  Then starts it.
             */
            @Override
            public void onClick(View v) {
                // change etUsername and etPassword text to string
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // validate username and password
                if (validateLogin(username, password)) {
                    // make new intent with this activity to homepage activity
                    if(userData.containsKey(username)){
                        Log.e(TAG, "Username gotten: " + userData.get(username).username);
                        Log.e(TAG, "Password gotten: " + userData.get(username).password);
                        Log.e(TAG, "email gotten: " + userData.get(username).email);
                        Log.e(TAG, "phone number gotten: " + userData.get(username).phoneNumber);
                        Log.e(TAG, "Account: " + userData.get(username));
                    }
                    User user = userData.get(username);
                    Intent intent = new Intent(getApplicationContext(), activity_screen_slide.class);
                    // put username into Intent with a name "username"
                    intent.putExtra("userObject", user);
                    //intent.putExtra("account", userData.get(username));
                    // start activity
                    startActivityForResult(intent,UPDATE_USER_RESULTFLAG);
                }
            }
        });

        /**
         onClickListener for sign up button.
         */
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            /**
             onClick method, when sign up button is clicked, create new intent from this activity to sign up activity.
             Then, start activity with result.
             */
            @Override
            public void onClick(View v) {
                // New intent from this activity to sign up activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                // put user data dictionary to "UserDictionary" in intent
                intent.putExtra("UserDictionary", userDict);
                // start activity intent with result with request 2.
                startActivityForResult(intent, UPDATE_USER_RESULTFLAG);
            }
        });
    }

    /**
     * Resets password text on resume
     */
    @Override
    public void onResume(){
        super.onResume();
        etPassword.setText("");
    }

    /**
     onActivityResult: get the result from an activity. if requested code is 2 and if the resultCode is RESULT_OK then addUser from
     intent.getSerializableExtra ("userObject"). From the signupactivity. it gets the data from it and adds the user into the dictionary.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == UPDATE_USER_RESULTFLAG) {
            if (resultCode == RESULT_OK) {
                addUser((User) intent.getSerializableExtra("userObject"));
            }
        }

    }

    /**
     addUser method takes in a user object and put it in the userData hashmap. It also sets the userData back to UserDict.
     * @param user - user object representing a user in the system.
     */
    private void addUser(User user) {
        userData.put(user.username, user);
        userDict.setUserDict(userData);
    }

    /**
     validateLogin method checks if the username and password are empty, if the username is unique (does not exist in the data dictionary),
     and if the password matches with the password for the username in the datadictionary. If the checks are true, display error message and toast message,
     and return false. If the username and password are validated and are correct return true.
     */
    private boolean validateLogin(String username, String password) {
        if (username.isEmpty()) {
            etUsername.setError("Field Cannot Be Empty");
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        } else if (password.isEmpty()) {
            etPassword.setError("Field Cannot Be Empty");
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        } else if (!userData.containsKey(username)) {
            etUsername.setError("Invalid Username");
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        } else if (!userData.get(username).password.equals(password)) {
            etPassword.setError("Invalid Password");
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        } else {
            return true;
        }
    }
}