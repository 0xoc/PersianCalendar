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

        // get a handle of all TextViews in the layout
        TextView day = (TextView) findViewById(R.id.day_day_view);
        TextView month = (TextView) findViewById((R.id.day_month_view));
        TextView year = (TextView)findViewById(R.id.day_year_view);
        TextView title = (TextView) findViewById(R.id.txtViewEvent);

        // set new font for all TextViews
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"mt.ttf");
        title.setTypeface(tf);
        day.setTypeface(tf);
        month.setTypeface(tf);
        year.setTypeface(tf);


        // year, month and day will be received through intent extras
        // -1 mean not "initialized"
        int y = -1;
        int m = -1;
        int d = -1;

        Intent intent = getIntent();
        if (intent != null){
            d = (int) intent.getExtras().get("DAY");
            m = (int) intent.getExtras().get("MONTH");
            y = (int) intent.getExtras().get("YEAR");
        }

        // set year, month and day texts

        // todo: check for initialization
        day.setText(d + "");
        month.setText(m+ "");
        year.setText(y + "");


        // get events of the specified date
        ArrayList<Event> thisDateEvents = Event.getEventsAt(new PersianDate(y,m,d));

        // setup the recycler view that is going to display all events of the specified date
        RecyclerView eventsView = (RecyclerView) findViewById(R.id.rc_event_view);
        EventViewAdapter adapter = new EventViewAdapter(this,thisDateEvents,false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsView.setAdapter(adapter);
        eventsView.setLayoutManager(layoutManager);
    }
}
