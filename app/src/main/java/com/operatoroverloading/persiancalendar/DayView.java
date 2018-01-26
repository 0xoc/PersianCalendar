package com.operatoroverloading.persiancalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DayView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        ArrayList<String> time = new ArrayList<>();
        int y = -1;
        int m = -1;
        int d = -1;

        Intent intent = getIntent();
        if (intent != null) {
            y = (int) intent.getExtras().get("YEAR");
            m = (int) intent.getExtras().get("MONTH");
            d = (int) intent.getExtras().get("DAY");
        }

        TextView year = (TextView) findViewById(R.id.day_year);
        TextView month = (TextView) findViewById(R.id.day_month);
        TextView day = (TextView) findViewById(R.id.day_day);

        if (y != -1)
            year.setText(y + "");
        if (m != -1)
            month.setText(m + "");
        if (d != -1)
            day.setText(d + "");


        for (int i = 0; i < 12;i+= 2){
            time.add(i + "-" + (i + 2));
        }

        ListView times = (ListView) findViewById(R.id.dayTimes);
        DayTimesAdapter timesAdapter = new DayTimesAdapter(this,R.layout.day_time_view,time);
        times.setAdapter(timesAdapter);
    }
}
