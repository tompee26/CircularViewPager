package com.tompee.samples.circularviewpagerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tompee.circularviewpager.CircularViewPager;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircularViewPager circularViewPager = (CircularViewPager) findViewById(R.id.view_pager);
        circularViewPager.setPageCount(2);

        circularViewPager.setLayoutPagerAdapter(new CircularViewPager.GetLayoutItemListener() {
            @Override
            public int getLayout(int position) {
                if (position == 0) {
                    return R.layout.layout_sample_blue;
                }
                return R.layout.layout_sample_red;
            }
        });

    }
}
