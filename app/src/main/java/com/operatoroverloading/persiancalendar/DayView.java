package com.operatoroverloading.persiancalendar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import CalendarCore.EventCore.Event;
import CalendarCore.EventCore.EventType;
import SinaPersianCalendar.PersianDate;

public class DayView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);


        // year, month and day will be received through intent extras
        // -1 mean not "initialized"
        int y = -1;
        int m = -1;
        int d = -1;

        // get intent and extract it's data
        Intent intent = getIntent();
        if (intent != null) {
            y = (int) intent.getExtras().get("YEAR");
            m = (int) intent.getExtras().get("MONTH");
            d = (int) intent.getExtras().get("DAY");
        }

        // get a handle of all the TextViews in the layout
        TextView year = (TextView) findViewById(R.id.day_year);
        final TextView month = (TextView) findViewById(R.id.day_month);
        final TextView day = (TextView) findViewById(R.id.day_day);
        TextView setupEvent = (TextView) findViewById(R.id.txtSetupEvent);

        // get a handle of other input types
        // they are declared final because they are used from inner bocks later on
        final Spinner eventType = (Spinner) findViewById(R.id.spEventType);
        final NumberPicker pickHour = (NumberPicker) findViewById(R.id.timeHour);
        final NumberPicker pickMin = (NumberPicker) findViewById(R.id.timeMinute);

        // set number picker properties
        pickHour.setMaxValue(23);
        pickHour.setMinValue(0);

        pickMin.setMinValue(0);
        pickMin.setMaxValue(59);

        pickHour.setWrapSelectorWheel(true);
        pickMin.setWrapSelectorWheel(true);


        // register new font
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "mt.ttf");

        // set new font for year, month and day
        year.setTypeface(tf);
        month.setTypeface(tf);
        day.setTypeface(tf);

        // set the received date info
        if (y != -1)
            year.setText(y + "");
        if (m != -1)
            month.setText(m + "");
        if (d != -1)
            day.setText(d + "");

        // get a list of all event types so far
        final int[] eventCount = {EventType.events.size()};
        String types[] = getEventList(eventCount[0]);          // getEventList returns an array of size (eventCount + 1)


        // create the spinner adapter
        ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types);
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // add a new event type
        eventType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int current = adapterView.getSelectedItemPosition();
                // copy eventCount (wee need to change it in inner class)
                if (current == eventCount[0]) {

                    // create a dialog box builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(DayView.this);
                    View newEventTypeView = getLayoutInflater().inflate(R.layout.new_event_type, null);

                    // get dialog elements
                    final TextView newEventTitle = newEventTypeView.findViewById(R.id.newEventTypeTitle);
                    Button addNewEventType = newEventTypeView.findViewById(R.id.addNewEventTypeBtn);

                    // create a dialog from dialog builder
                    builder.setView(newEventTypeView);
                    final AlertDialog dialog = builder.create();

                    // register the new event type
                    addNewEventType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String eventTypeTitle = newEventTitle.getText().toString();
                            EventType tmp = new EventType(eventTypeTitle);
                            Toast.makeText(getApplicationContext(), "نوع رویداد جدید اضافه شد", Toast.LENGTH_SHORT).show();

                            // update spinner with new data
                            int newSize = EventType.events.size();

                            // increase event count by 1
                            eventCount[0]++;

                            String newData[] = getEventList(newSize);
                            ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(DayView.this, android.R.layout.simple_spinner_dropdown_item, newData);
                            eventType.setAdapter(newAdapter);


                            // hide the dialog
                            dialog.hide();
                        }
                    });

                    dialog.show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // set spinner adapter
        eventType.setAdapter(eventTypeAdapter);


        setupEvent.setTypeface(tf);
        ImageButton addEventBtn = (ImageButton) findViewById(R.id.addEventBtn);


        final PersianDate eventDate = new PersianDate(y, m, d);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // register new event
                TextView eventTitle = (TextView) findViewById(R.id.txtEventTitle);
                TextView eventLocation = (TextView) findViewById(R.id.txtEventLocation);
                TextView eventDescription = (TextView) findViewById(R.id.txtEventDescription);

                int hour = pickHour.getValue();
                int min = pickMin.getValue();

                Event newEvent = new Event(eventTitle.getText().toString(), eventDescription.getText().toString(),
                        eventLocation.getText().toString(), eventDate, hour, min, EventType.events.get(eventType.getSelectedItemPosition()));


                // convert date from s to g
                Roozh dateConverter = new Roozh();
                dateConverter.PersianToGregorian(newEvent.getDate().getYear(),newEvent.getDate().getMonth(),newEvent.getDate().getDay());

                // make Calendar instance from converted date
                Calendar startDate = Calendar.getInstance();
                startDate.set(dateConverter.gY,dateConverter.gM - 1,dateConverter.gD,0,0);

                //
                long start = startDate.getTimeInMillis() + (newEvent.getHour())*(3600 * 1000) + newEvent.getMin()*(60000);
                long end = start;
                // add event to google calendar also
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, start);
                values.put(CalendarContract.Events.DTEND, end);
                values.put(CalendarContract.Events.TITLE, newEvent.getTitle());
                values.put(CalendarContract.Events.HAS_ALARM, 1);
                values.put(CalendarContract.Events.DESCRIPTION, newEvent.getDescription());
                values.put(CalendarContract.Events.EVENT_LOCATION, newEvent.getLocation());
                values.put(CalendarContract.Events.CALENDAR_ID, 1);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, startDate.getTimeZone().toString());
                if (ActivityCompat.checkSelfPermission(DayView.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

                // set alarm

                long eventID = Long.parseLong(uri.getLastPathSegment());
                values = new ContentValues();
                values.put(CalendarContract.Reminders.MINUTES, 0);
                values.put(CalendarContract.Reminders.EVENT_ID, eventID);
                values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
                uri = getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);
                Toast.makeText(getApplicationContext(),"New Event added",Toast.LENGTH_SHORT).show();


            }
        });

    }

    public String [] getEventList (int eventCount) {
        String types[] = new String[eventCount + 1];
        for (int i = 0; i < eventCount;i++){
            types[i] = EventType.events.get(i).getEventTitle();
        }
        types[eventCount] = "اضافه کردن نوع جدید...";       // to allow the user to create a new event type
        return types;
    }
}