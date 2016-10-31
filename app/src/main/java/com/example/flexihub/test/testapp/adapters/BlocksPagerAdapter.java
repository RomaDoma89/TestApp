package com.example.flexihub.test.testapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Roman Zahorui on 27.10.2016.
 */

public class BlocksPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> blocks = new ArrayList<>();

    public BlocksPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return blocks.get(position);
    }

    @Override
    public int getCount() {
        return blocks.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return blocks.get(position).getArguments().getString("TITLE");
    }

    public void addBlock(Fragment fragment) {
        blocks.add(fragment);
    }
}
