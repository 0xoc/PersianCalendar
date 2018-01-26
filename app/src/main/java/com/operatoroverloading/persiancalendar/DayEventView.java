package com.operatoroverloading.persiancalendar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import CalendarCore.EventCore.Event;
import SinaPersianCalendar.PersianDate;

public class DayEventView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_event_view);
        TextView day = (TextView) findViewById(R.id.day_day_view);
        TextView month = (TextView) findViewById((R.id.day_month_view));
        TextView year = (TextView)findViewById(R.id.day_year_view);

        // change toolbar font
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"mt.ttf");
        TextView title = (TextView) findViewById(R.id.txtViewEvent);
        title.setTypeface(tf);
        day.setTypeface(tf);
        month.setTypeface(tf);
        year.setTypeface(tf);
        int y = -1;
        int m = -1;
        int d = -1;

        Intent intent = getIntent();
        if (intent != null){
            d = (int) intent.getExtras().get("DAY");
            m = (int) intent.getExtras().get("MONTH");
            y = (int) intent.getExtras().get("YEAR");
        }
        day.setText(d + "");
        month.setText(m+ "");
        year.setText(y + "");

        RecyclerView eventsView = (RecyclerView) findViewById(R.id.rc_event_view);
        ArrayList<Event> thisDateEvents = Event.getEventsAt(new PersianDate(y,m,d));

        EventViewAdapter adapter = new EventViewAdapter(this,thisDateEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsView.setAdapter(adapter);
        eventsView.setLayoutManager(layoutManager);
    }
}
