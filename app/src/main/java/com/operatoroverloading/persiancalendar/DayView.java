package com.operatoroverloading.persiancalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DayView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        ArrayList<String> time = new ArrayList<>();

        for (int i = 0; i < 12;i+= 2){
            time.add(i + "-" + (i + 2));
        }

        ListView times = (ListView) findViewById(R.id.dayTimes);
        DayTimesAdapter timesAdapter = new DayTimesAdapter(this,R.layout.day_time_view,time);
        times.setAdapter(timesAdapter);
    }
}
