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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

abstract class CircularFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Integer> mPageList;

    public CircularFragmentPagerAdapter(FragmentManager fragmentManager, int count) {
        super(fragmentManager);
        mPageList = new ArrayList<>();
        mPageList.add(count + 1);
        for (int index = 0; index < count; index++) {
            mPageList.add(index + 1);
        }
        mPageList.add(0);
    }

    public int getCount() {
        return mPageList.size() > 2 ? mPageList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == mPageList.size() - 1) {
            return getFragment(0);
        } else if (position == 0) {
            return getFragment(mPageList.size() - 3);
        }
        return getFragment(mPageList.get(position) - 1);
    }

    protected abstract Fragment getFragment(int position);
}
