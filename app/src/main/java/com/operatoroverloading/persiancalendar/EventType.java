package com.operatoroverloading.persiancalendar;

import java.util.ArrayList;

/**
 * Created by sina on 1/19/18.
 */

public class EventType {
    public static ArrayList<EventType> events = new ArrayList<>();

    private String eventTitle;

    public EventType(String eventTitle) {
        this.eventTitle = eventTitle;
        events.add(this);
    }

    public String getEventTitle() {
        return eventTitle;
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
