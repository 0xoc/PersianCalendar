package com.operatoroverloading.persiancalendar;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import SinaPersianCalendar.PersianDate;
import SinaPersianCalendar.PersianMonth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }


        PersianDate today = new PersianDate() ;
        PersianMonth thisMonth =new PersianMonth(today.getYear(),today.getMonth()) ;
        ShowCalendar(thisMonth);


        //Typeface
        TextView day0 = (TextView) findViewById(R.id.day0text);
        TextView day1 = (TextView) findViewById(R.id.day1text);
        TextView day2 = (TextView) findViewById(R.id.day2text);
        TextView day3 = (TextView) findViewById(R.id.day3text);
        TextView day4 = (TextView) findViewById(R.id.day4text);
        TextView day5 = (TextView) findViewById(R.id.day5text);
        TextView day6 = (TextView) findViewById(R.id.day6text);
        Typeface tf = Typeface.createFromAsset(getAssets(),"mt.ttf");
        day0.setTypeface(tf);
        day1.setTypeface(tf);
        day2.setTypeface(tf);
        day3.setTypeface(tf);
        day4.setTypeface(tf);
        day5.setTypeface(tf);
        day6.setTypeface(tf);


    }


    public void ShowCalendar(PersianMonth month){
        //PersianDate today = new PersianDate( );
        CalendarAdapter ca;
        ca = new CalendarAdapter(MainActivity.this, month.getDays());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
    }


}