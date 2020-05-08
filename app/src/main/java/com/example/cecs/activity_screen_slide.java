package com.example.cecs;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class activity_screen_slide extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    /**
     * Create dots that indicate which page view is on
     */
    private LinearLayout dotLayout;
    private TextView[] dots;

    private Button backButton;
    private Button nextButton;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        dotLayout = findViewById(R.id.dotsLayout);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.prevButton);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        //initiate dots
        addDotsIndicator(0);
        mPager.addOnPageChangeListener((viewListener));

        //buttons change page
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(currentPage + 1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    public void addDotsIndicator(int position){
        dots = new TextView[NUM_PAGES];
        dotLayout.removeAllViews();;
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView((this));
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            dotLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(android.graphics.Color.WHITE);
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

            if(currentPage == 0){
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);

            }
            else if(currentPage == dots.length - 1){
                nextButton.setEnabled(false);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
            }
            else{
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        Context context;
        LayoutInflater layoutInflater;
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fr;
            if(position == 0) {
                fr =  new MainMenuActivity();
                //Get Intent?
            }
            else {
                fr =  new SettingsActivity();
            }
            return fr;
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }




}