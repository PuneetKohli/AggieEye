package com.diversityhack.placespace.aggieeye;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diversityhack.placespace.aggieeye.Fragments.HistoryCheckFragments.HC_Fragment1;
import com.diversityhack.placespace.aggieeye.Fragments.HistoryCheckFragments.HC_Fragment2;
import com.viewpagerindicator.LinePageIndicator;

public class HistoryChecksActivity extends AppCompatActivity {

    TextView text_next;
    View button_next;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_checks);

        text_next = (TextView) findViewById(R.id.text_next);
        button_next = findViewById(R.id.button_next);

        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        LinePageIndicator pagerIndicator = (LinePageIndicator) findViewById(R.id.indicator);
        pagerIndicator.setViewPager(pager);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager.getCurrentItem() < pager.getAdapter().getCount() - 1)
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                else
                    submit();
            }
        });

        pagerIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == pager.getAdapter().getCount() - 1)
                    text_next.setText("Submit");
                else
                    text_next.setText("Next");
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void submit() {
        Toast.makeText(this, "Your data has been submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return HC_Fragment1.newInstance("One");
                case 1: return HC_Fragment2.newInstance("Two");
                case 2: return HC_Fragment2.newInstance("Two - 2");
                case 3: return HC_Fragment2.newInstance("Two - 3");
                case 4: return HC_Fragment2.newInstance("Two - 4");
                default: return HC_Fragment2.newInstance("Default");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
