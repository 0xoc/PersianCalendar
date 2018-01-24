package com.operatoroverloading.persiancalendar;

import android.provider.ContactsContract;

import java.util.Calendar;

import SinaPersianCalendar.PersianDate;

/**
 * Created by hossein on 1/19/18.
 */

public class Event {
    
    String eventText = "" ;
    PersianDate eventDate ;
    EventType et  = EventType.NORMAL;
    String eventTime ;

}
