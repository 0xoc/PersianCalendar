package CalendarCore.EventCore;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import SinaPersianCalendar.PersianDate;

/**
 * Created by hossein on 1/19/18.
 */

public class Event implements Serializable,Comparable{
    public static ArrayList<Event> events = new ArrayList<>();
    private String title;
    private String description;
    private String location;
    private PersianDate date;
    private int hour;
    private int min;

    private EventType type;


    // get all the events of a date
    public static ArrayList<Event> getEventsAt(PersianDate date) {
        ArrayList<Event> dateEvents = new ArrayList<>();
        for (int i = 0 ; i < events.size(); i ++) {
            if (events.get(i).getDate().equals(date)){
                dateEvents.add(events.get(i));
            }
        }
        return dateEvents;
    }


    public Event(String title, String description, String location, PersianDate date, int hour, int min, EventType type) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.hour = hour;
        this.min = min;
        this.type = type;
        events.add(this);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PersianDate getDate() {
        return date;
    }

    public void setDate(PersianDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static void saveData (Context context){
        // save the size of the data
        try {
            FileOutputStream out = context.openFileOutput("eventSize",0);
            out.write(events.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("SAVE_ERROR","Could not open event size file");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SAVE_ERROR","Could not save event size");
        }

        // save all the data
        for (int i = 0 ; i < events.size();i ++){
            try {
                FileOutputStream out = context.openFileOutput("event" + i,0);
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(events.get(i));
                Log.i("SAVE_DONE","Event data saved");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("SAVE_ERROR","Could not open event file " + i);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("SAVE_ERRIR", "Could not create object out event " + i);
            }
        }
    }

    @Override
    public int compareTo(@NonNull Object o) {
        int y =this.date.getYear();
        int m = this.date.getMonth();
        int d = this.date.getDay();

        Event rhs = (Event) o;

        // compare two events based on their date and time
        if (y > rhs.getDate().getYear()) {
            return 1;
        } else if (y == rhs.getDate().getYear()) {

            // if year is the same compare month
            if(m > rhs.getDate().getMonth()) {
                return 1;   // this is a "bigger evnet"
            } else if (m == rhs.getDate().getMonth()) {
                // if month is the same compare days
                if (d > rhs.getDate().getDay()){
                    return 1;   // this is a "bigger event"
                } else if (d == rhs.getDate().getDay()){
                    // if day is the same compare times

                    if (this.hour > rhs.getHour()) {
                        return 1;
                    } else if (this.hour == rhs.getHour()){

                        if (this.min > rhs.getMin()){
                            return 1;
                        } else if (this.min == rhs.getMin()) {
                            return 0;   // the two events are "equal"
                        } else {
                            return -1;
                        }

                    } else {
                        return -1;
                    }

                } else {
                    // this is a "smaller" event
                    return -1;
                }
            } else {
                // this is a "smaller" event
                return -1;
            }
        } else {
            // this is a "smaller" event
            return -1;
        }
    }
}
