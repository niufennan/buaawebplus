package com.webplus.jiangchao.iridescence.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MyFragmentPagerAdapter  extends FragmentPagerAdapter {
        private ArrayList<Fragment> listFragments;
        private int currentPosition;

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {

            currentPosition =position;
            super.setPrimaryItem(container, position, object);
        }
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
            super(fm);
            listFragments = al;
        }

       public int getCurrentPosition()
    {
        return currentPosition;
    }


        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
}

