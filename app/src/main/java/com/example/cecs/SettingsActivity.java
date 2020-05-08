package com.example.cecs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class SettingsActivity extends Fragment {
    Button logoutBtn;
    Button aboutBtn;
    Button helpBtn;
    Button changePasswordBtn;
    Button changeProfileBtn;
    private UserDataDictionary userDict = new UserDataDictionary();
    private HashMap<String, User> userData = userDict.getUserDict();
    private String TAG = SettingsActivity.class.getSimpleName();



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

        Intent accountIntent = getActivity().getIntent();
        String userName = accountIntent.getStringExtra("username");
        Log.e(TAG, "Username that is logged in: " + userName);
        Log.e(TAG, "Username Exists: " + userData.containsKey(userName));

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePasswordIntent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(changePasswordIntent);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
                logoutBtn.setText("OK");
            }
        });
    }
}
