package com.operatoroverloading.persiancalendar;

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

        

        // refrace to day buttons
//        Button dayButtons[] = new Button[35];
//        for (int i = 1; i <= 35; i++) {
//            int id = getResources().getIdentifier("d" + i, "id", getPackageName());
//            dayButtons[i - 1] = (Button) findViewById(id);
//        }


        PersianDate today = new PersianDate() ;
        PersianMonth thisMonth =new PersianMonth(today.getYear(),today.getMonth()) ;
        ShowCalendar(thisMonth);



    }


    public void ShowCalendar(PersianMonth month){

        PersianDate today = new PersianDate( );
        TextView today_date = (TextView)findViewById(R.id.txt_Today);
        today_date.setText(today.getDate().toString());


        CalendarAdapter ca;
        ca = new CalendarAdapter(MainActivity.this, month.getDays());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
    }


    public void btn_Goto_click(View v){
        EditText txt_month = (EditText)findViewById(R.id.txt_month_goto);
        EditText txt_year = (EditText) findViewById(R.id.txt_year_goto);

        int goto_month =Integer.parseInt( txt_month.getText().toString()) ;
        int goto_year = Integer.parseInt(txt_year.getText().toString());

        PersianMonth wanted_Month = new PersianMonth(goto_year,goto_month);
        ShowCalendar(wanted_Month);

    }
}