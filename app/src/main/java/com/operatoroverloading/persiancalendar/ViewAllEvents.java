package com.operatoroverloading.persiancalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;

import CalendarCore.EventCore.Event;

public class ViewAllEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);

        // get the recycler view for all events
        RecyclerView allEvents = (RecyclerView) findViewById(R.id.rcAllEvents);

        // sort events base on their date and time
        Collections.sort(Event.events);

        // create a new adapter
        EventViewAdapter adapter = new EventViewAdapter(this, Event.events,true);

        // create a layout manger for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // set the adapter and layout manger to the recycler view
        allEvents.setAdapter(adapter);
        allEvents.setLayoutManager(layoutManager);
    }
}
