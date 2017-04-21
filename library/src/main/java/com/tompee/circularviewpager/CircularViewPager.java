/**
 * Copyright (C) 2017 tompee
 * <p>
 * MIT License
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.tompee.circularviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class CircularViewPager extends ViewPager {
    private final CircularViewPagerListener mListener;
    private int mPageCount;

    public CircularViewPager(Context context) {
        this(context, null);
    }

    public CircularViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        mListener = new CircularViewPagerListener();
        setOverScrollMode(OVER_SCROLL_NEVER);
        setOffscreenPageLimit(1);
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.CircularViewPager, 0, 0);
        mPageCount = a.getInteger(R.styleable.CircularViewPager_pageCount, 0);
        a.recycle();
    }

    public void setFragmentPagerAdapter(FragmentManager manager, final GetFragmentItemListener listener) {
        CircularFragmentPagerAdapter adapter = new CircularFragmentPagerAdapter(manager, mPageCount) {
            @Override
            protected Fragment getFragment(int position) {
                return listener.getFragment(position);
            }
        };
        setAdapter(adapter);
    }

    public void setLayoutPagerAdapter(final GetLayoutItemListener listener) {
        LayoutPagerAdapter adapter = new LayoutPagerAdapter(mPageCount) {
            @Override
            protected int getLayout(int position) {
                return listener.getLayout(position);
            }
        };
        setAdapter(adapter);
    }

    public void setPageCount(int count) {
        mPageCount = count;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        addOnPageChangeListener(mListener);
        setCurrentItem(1);
    }

    public interface GetFragmentItemListener {
        Fragment getFragment(int position);
    }

    public interface GetLayoutItemListener {
        int getLayout(int position);
    }


    private class CircularViewPagerListener implements OnPageChangeListener {
        private static final int DELAY = 200;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    loopCurrentItem(position);
                }
            }, DELAY);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        private void loopCurrentItem(int position) {
            int pageCount = getAdapter().getCount();
            if (position == pageCount - 1) {
                setCurrentItem(1, false);
            } else if (position == 0) {
                setCurrentItem(pageCount - 2, false);
            }
        }
    }
}
