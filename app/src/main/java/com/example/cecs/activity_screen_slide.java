package com.example.cecs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        //mPager.setBackgroundColor(Color.parseColor("#f56c42"));
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        //mPager.setPageTransformer(true, new ZoomOutPageTransformer());
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
        /*
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (RelativeLayout) object;
        }

         */

        /*
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            if(position == 0) {
                //view = layoutInflater.inflate(R.layout.activity_main_menu, container, false);
                view = layoutInflater.inflate(R.layout.activity_settings, container, false);

            }
            else {
                view = layoutInflater.inflate(R.layout.activity_settings, container, false);
            }

            container.addView(view);
            return view;
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((RelativeLayout)object);
        }
        */
    }




}
