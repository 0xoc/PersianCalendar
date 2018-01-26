package com.operatoroverloading.persiancalendar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import CalendarCore.EventCore.Event;
import CalendarCore.EventCore.EventType;
import SinaPersianCalendar.PersianDate;

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

        final Spinner eventType = (Spinner) findViewById(R.id.spEventType);

        EventType n = new EventType("تایم کلاس ها");
        EventType a = new EventType("قرار ملاقات");
        EventType b = new EventType("همورک");
        final int eventCount = EventType.events.size();


        String types[] = null;
        types = new String[eventCount + 1];

        for (int i = 0; i < eventCount;i++){
            types[i] = EventType.events.get(i).getEventTitle();
        }
        types[eventCount] = "اضافه کردن نوع جدید...";

        eventType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int current = adapterView.getSelectedItemPosition();
                if (current == eventCount)
                    Toast.makeText(getApplicationContext(),"todo: add new event in dialog",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventType.setAdapter(eventTypeAdapter);


        final NumberPicker pickHour = (NumberPicker) findViewById(R.id.timeHour);
        final NumberPicker pickMin = (NumberPicker) findViewById(R.id.timeMinute);

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
        ImageButton addEventBtn = (ImageButton) findViewById(R.id.addEventBtn);


        final PersianDate eventDate = new PersianDate(y,m,d);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // register new event
                TextView eventTitle = (TextView) findViewById(R.id.txtEventTitle);
                TextView eventLocation = (TextView) findViewById(R.id.txtEventLocation);
                TextView eventDescription = (TextView) findViewById(R.id.txtEventDescription);

                int hour = pickHour.getValue();
                int min = pickMin.getValue();

                Event newEvent = new Event(eventTitle.getText().toString(),eventDescription.getText().toString(),
                        eventLocation.getText().toString(),eventDate,hour,min,EventType.events.get(eventType.getSelectedItemPosition()));
                Toast.makeText(getApplicationContext(),"New Event added",Toast.LENGTH_SHORT).show();
            }
        });

    }
}