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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        final PersianDate today = new PersianDate();
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        MonthViewAdapter adapter = new MonthViewAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(today.getMonth() - 1);

        Button gotoToday = (Button) findViewById(R.id.gotoToday);
        gotoToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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