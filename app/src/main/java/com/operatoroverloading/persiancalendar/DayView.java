package com.operatoroverloading.persiancalendar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
        TextView setupEvent = (TextView) findViewById(R.id.txtSetupEvent);

        if (y != -1)
            year.setText(y + "");
        if (m != -1)
            month.setText(m + "");
        if (d != -1)
            day.setText(d + "");

        Spinner evenyType = (Spinner) findViewById(R.id.spEventType);
        
        NumberPicker pickHour = (NumberPicker) findViewById(R.id.timeHour);
        NumberPicker pickMin = (NumberPicker) findViewById(R.id.timeMinute);

        pickHour.setMaxValue(24);
        pickHour.setMinValue(1);

        pickMin.setMinValue(0);
        pickMin.setMaxValue(59);

        pickHour.setWrapSelectorWheel(true);
        pickMin.setWrapSelectorWheel(true);

        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"mt.ttf");
        year.setTypeface(tf);
        month.setTypeface(tf);
        day.setTypeface(tf);
        setupEvent.setTypeface(tf);

    }
}
