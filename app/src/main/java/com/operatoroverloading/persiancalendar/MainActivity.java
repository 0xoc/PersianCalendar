package com.operatoroverloading.persiancalendar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import CalendarCore.EventCore.Event;
import CalendarCore.EventCore.EventType;
import SinaPersianCalendar.PersianDate;
import SinaPersianCalendar.PersianMonth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static MonthViewAdapter monthViewAdapter;
    private static ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        LayoutInflater inflater = LayoutInflater.from(this);

        final PersianDate today = new PersianDate();
        pager = (ViewPager) findViewById(R.id.pager);
        monthViewAdapter = new MonthViewAdapter(getSupportFragmentManager());
        pager.setAdapter(monthViewAdapter);
        pager.setCurrentItem(today.getMonth() - 1);



        // home button
        ImageButton gotoToday = (ImageButton) findViewById(R.id.gotoToday);
        gotoToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setYear(today.getYear());
                pager.setCurrentItem(today.getMonth() - 1);

            }
        });


        // add new event for today
        ImageButton add = (ImageButton) findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DayView.class);
                intent.putExtra("YEAR",today.getYear());
                intent.putExtra("MONTH",today.getMonth());
                intent.putExtra("DAY",today.getDay());
                startActivity(intent);
            }
        });

        // change fonts for text views
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

        // load data
        loadEventTypeData(this);
        loadEventData(this);

        //Event event = new Event("تیتر رویداد","توضیحات","hi",new PersianDate(),12,45,new EventType("همورک"));
    }

    // load all the event types
    public static void loadEventTypeData (Context context) {
         // get event type size
        int size = 0;
        try {
            FileInputStream in = context.openFileInput("eventTypeSize");
            size = in.read();
            Log.i("EVENT_SIZE",size + "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("LOAD_ERROR","Could not open event type size");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LOAD_ERROR","Could not load event type size");
        }

        for (int i = 0; i < size;i++){
            try {
                FileInputStream in = context.openFileInput("eventType"+i);
                ObjectInputStream oin = new ObjectInputStream(in);
                EventType.events.add((EventType) oin.readObject());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not open event type file " + i);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not create event type object " + i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not read event type object " + i);
            }
        }
    }


    // load all events
    public static void loadEventData (Context context) {
        int size = 0;
        try {
            FileInputStream in = context.openFileInput("eventSize");
            size = in.read();
            Log.i("EVENT_SIZE",size + "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("LOAD_ERROR","Could not open event size");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LOAD_ERROR","Could not load event size");
        }

        for (int i = 0; i < size;i++){
            try {
                FileInputStream in = context.openFileInput("event"+i);
                ObjectInputStream oin = new ObjectInputStream(in);
                Event.events.add((Event) oin.readObject());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not open event file " + i);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not create event object " + i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("LOAD_ERROR","Could not read event object " + i);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // save data before exit
        EventType.saveData(MainActivity.this);
        Event.saveData(MainActivity.this);
    }

    // set the current calendar view to a specific year
    public static void setYear (int year) {
        monthViewAdapter.year = year;
        pager.setAdapter(monthViewAdapter);
    }

    // seek the calendar view to the next year
    public static void nextYear () {
        int y = monthViewAdapter.getYear();
        setYear(y + 1);
    }

    // seek back the calendar view to previous year
    public static void previousYear () {
        int y = monthViewAdapter.getYear();
        setYear(y - 1);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_date_convert) {
            // Handle the camera action
        } else if (id == R.id.nav_events){
            Intent intent = new Intent(MainActivity.this,ViewAllEvents.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}