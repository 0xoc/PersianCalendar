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
    public MonthViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PersianDate today = new PersianDate();
        MonthViewFragment month = new MonthViewFragment(today.getYear(),position + 1,PersianDate.monthIntToString(position));
        return month;
    }

    @Override
    public int getCount() {
        return count;
    }
}
