package com.example.cecs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsActivity extends Fragment {
    Button logoutBtn;
    Button aboutBtn;
    Button helpBtn;
    Button changePasswordBtn;
    Button changeProfileBtn;
    TextView welcomeMessage;
    private User user;
    private String TAG = SettingsActivity.class.getSimpleName();
    private final int CHANGE_PASSWORD_RESULTFLAG = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_settings, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logoutBtn = getView().findViewById(R.id.logoutButton);
        aboutBtn = getView().findViewById(R.id.aboutButton);
        helpBtn = getView().findViewById(R.id.helpButton);
        changePasswordBtn = getView().findViewById(R.id.changePasswordButton);
        welcomeMessage = getView().findViewById(R.id.accountLoggedIn);


        Intent accountIntent = getActivity().getIntent();
        user = (User) accountIntent.getSerializableExtra("userObject");
        welcomeMessage.setText("Username that is logged in: " + user.username);

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePasswordIntent = new Intent(getContext(), ChangePasswordActivity.class);

                changePasswordIntent.putExtra("userObject", user);
                startActivityForResult(changePasswordIntent, CHANGE_PASSWORD_RESULTFLAG);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(getContext(), AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(getContext(), HelpActivity.class);
                startActivity(helpIntent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * onActivityResult: get the result from an activity. if requested code is 2 and if the resultCode is RESULT_OK then addUser from
     * intent.getSerializableExtra ("userObject"). From the signupactivity. it gets the data from it and adds the user into the dictionary.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHANGE_PASSWORD_RESULTFLAG) {
            if (resultCode == getActivity().RESULT_OK) {
                user = ((User) intent.getSerializableExtra("userObject"));
                getActivity().getIntent().putExtra("userObject", user);
                getActivity().setResult(Activity.RESULT_OK, getActivity().getIntent());

            }
        }
    }
}
