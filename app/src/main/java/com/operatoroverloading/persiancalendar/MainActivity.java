package com.operatoroverloading.persiancalendar;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
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

        PersianMonth mehr = new PersianMonth(1396,7);

        CalendarAdapter ca;

        ca = new CalendarAdapter(MainActivity.this, mehr.getDays());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));




    }
}