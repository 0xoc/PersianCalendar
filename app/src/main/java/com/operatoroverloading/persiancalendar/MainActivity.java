package com.operatoroverloading.persiancalendar;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewPager;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import SinaPersianCalendar.PersianDate;
import SinaPersianCalendar.PersianMonth;

public class MainActivity extends AppCompatActivity {
    private static MonthViewAdapter monthViewAdapter;
    private static ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        LayoutInflater inflater = LayoutInflater.from(this);

        final PersianDate today = new PersianDate();
        pager = (ViewPager) findViewById(R.id.pager);
        monthViewAdapter = new MonthViewAdapter(getSupportFragmentManager());
        pager.setAdapter(monthViewAdapter);
        pager.setCurrentItem(today.getMonth() - 1);

        ImageButton gotoToday = (ImageButton) findViewById(R.id.gotoToday);
        gotoToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setYear(today.getYear());
                pager.setCurrentItem(today.getMonth() - 1);

            }
        });

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

    public static void setYear (int year) {
        monthViewAdapter.year = year;
        pager.setAdapter(monthViewAdapter);
    }
    public static void nextYear () {
        int y = monthViewAdapter.getYear();
        setYear(y + 1);
    }

    public static void previousYear () {
        int y = monthViewAdapter.getYear();
        setYear(y - 1);
    }
    public void ShowCalendar(PersianDate today){
        //PersianDate today = new PersianDate( );
        CalendarAdapter ca;
        PersianMonth month =new PersianMonth(today.getYear()  ,today.getMonth()) ;
        ca = new CalendarAdapter(MainActivity.this, month.getDays(),today);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
    }


}