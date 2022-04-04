package com.example.sa_f.fragment_f;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    //private static final String[] TAB_TITLES = new String[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final List<Fragment> lst = new ArrayList<>();
    private final List<String> title = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lst.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return title.size();
    }


    public void AddFragment(Fragment fragment, String name){
        lst.add(fragment);
        title.add(name);
    }
}