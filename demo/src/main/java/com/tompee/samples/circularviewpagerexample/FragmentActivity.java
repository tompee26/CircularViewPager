package com.tompee.samples.circularviewpagerexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.tompee.samples.circularviewpagerexample.fragment.SampleFragment;
import com.tompee.circularviewpager.CircularViewPager;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircularViewPager circularViewPager = (CircularViewPager) findViewById(R.id.view_pager);
        circularViewPager.setPageCount(4);

        circularViewPager.setFragmentPagerAdapter(getSupportFragmentManager(),
                new CircularViewPager.GetFragmentItemListener() {
                    @Override
                    public Fragment getFragment(int position) {
                        return SampleFragment.newInstance(position);
                    }
                });
    }
}
