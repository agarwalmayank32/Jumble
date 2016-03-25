package com.mayank.jumble;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Jumble", "Hit", "Hit Stats" };

    public TabPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new Jumble();
        } else if(position == 1) {
            return new Hit();
        } else if(position == 2) {
            return new HitStat();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
