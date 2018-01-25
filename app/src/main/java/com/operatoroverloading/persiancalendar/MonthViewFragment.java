package com.operatoroverloading.persiancalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import SinaPersianCalendar.PersianDate;
import SinaPersianCalendar.PersianMonth;

/**
 * Created by sina on 1/25/18.
 */

public class MonthViewFragment extends android.support.v4.app.Fragment {
    private View v;
    private int y;
    private int m;
    private String monthName;
    public MonthViewFragment() {}

    @SuppressLint("ValidFragment")
    public MonthViewFragment(int y, int m,String monthName) {
        this.y = y;
        this.m = m;
        this.monthName = monthName;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.month_view,container,false);
        TextView monthMonth = v.findViewById(R.id.monthMonth);
        monthMonth.setText(this.monthName);
        TextView monthYear = v.findViewById(R.id.monthY);

        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"mt.ttf");
        monthYear.setTypeface(tf);
        monthYear.setText(this.y + "");
        monthMonth.setTypeface(tf);
        ShowCalendar(y,m);
        return v;
    }

    public void ShowCalendar(int year,int monthN){
        PersianDate today = new PersianDate();
        CalendarAdapter ca;
        PersianMonth month =new PersianMonth(year  ,monthN) ;
        ca = new CalendarAdapter(getContext(), month.getDays(),today);
        RecyclerView recyclerView = v.findViewById(R.id.drawerList);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
    }
}
