package CalendarCore.EventCore;

import android.provider.ContactsContract;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import SinaPersianCalendar.PersianDate;

/**
 * Created by hossein on 1/19/18.
 */

public class Event {
    public static ArrayList<Event> events = new ArrayList<>();
    private String title;
    private String description;
    private String location;
    private PersianDate date;
    private int hour;
    private int min;

    private EventType type;


    // get event in a date
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
}
