package com.operatoroverloading.persiancalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import SinaPersianCalendar.PersianDate;

/**
 * Created by sina on 1/25/18.
 */

public class MonthViewAdapter extends FragmentStatePagerAdapter{
    private static int count = 12;
    private static PersianDate today = new PersianDate();
    public int year = 1398;

    public MonthViewAdapter(FragmentManager fm) {
        super(fm);
    }
    public int getYear () {return this.year;}
    @Override
    public Fragment getItem(int position) {
        MonthViewFragment month = new MonthViewFragment(year,position + 1,PersianDate.monthIntToString(position));
        return month;
    }

    @Override
    public int getCount() {
        return count;
    }
}
