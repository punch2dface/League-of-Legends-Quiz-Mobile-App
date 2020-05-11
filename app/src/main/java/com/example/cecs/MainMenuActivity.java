package com.example.cecs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainMenuActivity extends Fragment {

    private Button buttonStartQuiz;
    private ImageView tierIV;
    private TextView tierTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_main_menu, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tierIV = getView().findViewById(R.id.menuTierIV);
        tierTV = getView().findViewById(R.id.menuTierTV);

        Intent resIntent = getActivity().getIntent();

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

       buttonStartQuiz = getView().findViewById(R.id.menuTakeQuizBtn);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
