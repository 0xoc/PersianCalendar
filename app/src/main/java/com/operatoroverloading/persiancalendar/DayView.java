package com.operatoroverloading.persiancalendar;

import android.content.Intent;
import android.graphics.Typeface;
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


        final int eventCount = EventType.events.size();


        String types[];
        types = getEventList(eventCount);
        types[eventCount] = "اضافه کردن نوع جدید...";

        // create the spinner adapter
        ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // add a new event type
        eventType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int current = adapterView.getSelectedItemPosition();
                if (current == eventCount){

                    // create a dialog box builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(DayView.this);
                    View newEventTypeView = getLayoutInflater().inflate(R.layout.new_event_type,null);

                    // get dialog elements
                    final TextView newEventTitle =  newEventTypeView.findViewById(R.id.newEventTypeTitle);
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
                            Toast.makeText(getApplicationContext(),"نوع رویداد جدید اضافه شد",Toast.LENGTH_SHORT).show();

                            // todo: update spinner

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

    public String [] getEventList (int eventCount) {
        String types[] = new String[eventCount + 1];
        for (int i = 0; i < eventCount;i++){
            types[i] = EventType.events.get(i).getEventTitle();
        }
        return types;
    }
}