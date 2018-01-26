package SinaPersianCalendar;

import java.util.Calendar;
import java.lang.StringBuilder;
import java.lang.String;

/**
 * Created by snpar on 10/13/2017.
 */


public class PersianDate {
    // constructors

    // constructs the object with todays date
    public PersianDate(){
        this(Calendar.getInstance());
    }
    //QUESTION

    /* constructs the object with manual values
     * IT IS ONLY USED FROM INSIDE THE CLASS 	| not true any more
     */
    public PersianDate(int y,int m,int d,int dw,boolean il) {
        year = y;
        month = m;
        day = d;
        dayOfWeek =dw;
        isLeap = il;
    }

    /*
     * converts a Calendar object date to a PersianDate
     * and constructs the object with the new PersianDate
     */
    public PersianDate(Calendar cal){
        PersianDate persianDate = convertToPersianDate(cal);
        construct(persianDate);
    }


    // returns an instance of the current object
    public PersianDate getDate(){return this;}
    //QUESTION : WHAT DIES THIS DO ALONE ???

    /*
    * setters
    * setter functions return false on failure
    * and 1 on successful assignment
    * */
    public boolean setYear(int y){
        if (!(y >= 1300 & y < 1500))
            return false;
        year = y;
        return true;
    }
    public boolean setMonth(int m){
        if (!(m > 0 & m <= 12))
            return false;
        month = m;
        return true;
    }
    public boolean setDay(int d){
        if (month <= 6){
            if(!(d > 0 & day <= 31))
                return false;
            day = d;
            return true;
        } else if(month > 6 & month != 12) {
            if(!(d > 0 & day <= 30))
                return false;
            day = d;
            return true;
        } else {
            if(!isLeap){
                if(!(d>0 & d<=29))
                    return false;
                day = d;
                return true;
            }else{
                if(!(d>0 & d<=30))
                    return false;
                day = d;
                return true;
            }
        }
    }
    public boolean setDayOfTheWeek(int dw){
        if (!(dw >= 0 & dw <= 6))
            return false;
        dayOfWeek = dw;
        return true;
    }


    // getters
    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getDay(){return day;}
    public int getDayOfWeek(){return dayOfWeek;}

    // returns a string representing the day of the week
    public String weekDayString(){
        switch (dayOfWeek){
            case 7:
                return "شنبه";
            case 1:
                return "یک شنبه";
            case 2:
                return "دوشتبه";
            case 3:
                return "سه شنبه";
            case 4:
                return "چهار شنبه";
            case 5:
                return "پنج شنبه";
            case 6:
                return "جمعه";
            default:
                return "";
        }
    }
    public String monthString() {
        return monthIntToString(month - 1);
    }

    public static String monthIntToString(int i){
        switch(i + 1) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                return "ماه اشتباه";
        }

    }
    // static utilities

    /*
     * receives a Calendar object and converts it to a PersianDate object
     * and return the new PersianDate object
     * */
    public static PersianDate convertToPersianDate(Calendar cal){

        // epoch date
        Calendar epoch = Calendar.getInstance(cal.getTimeZone());
        epoch.set(epochY,epochM - 1,epochDay);

        // Calculate days between epoch and "cal's" date
        long difSec = (cal.getTimeInMillis() - epoch.getTimeInMillis());
        int days = (int)(difSec/(1000*3600*24))+1;

        /* convert days to Persian year
         * and Persian Month and Persian Day
         */
        int persianYear = makeYear(days);
        int persianMonth = makeMonth(days);
        int persianDay = makeDay(days);


        PersianDate newDate = new PersianDate(persianYear,persianMonth,persianDay,
                cal.get(Calendar.DAY_OF_WEEK),isYearLeap(persianYear));

        return newDate;
    }

    // is a given year leap or not!
    public static boolean isYearLeap(int y){
        if(y % 4 == 0)
            return true;
        return false;
    }

    // utilities

    // returns a string representation of the current date of the object
    public String toString() {
        StringBuilder dateString = new StringBuilder();
        dateString.append("");
        //dateString.append("امروز ");
        dateString.append(""+weekDayString()+" ");
        dateString.append(day + " ");
        dateString.append(monthString() + " ");
        dateString.append(year + " ");
        dateString.append(" |::| ");
        // create the string "Persian Style!"
        dateString.append(year);
        dateString.append("/");
        dateString.append(month);
        dateString.append("/");
        dateString.append(day);
        return dateString.toString();
    }

    // static helpers

    /*
     * the make* methods receive an amount of day
     * and return a * made with does days
     * */
    private static int makeYear(int d){
        int newYear = jalaliEpochYear;
        while(!(d <= 365)){
            if(newYear%4 == 0){
                if(d >= 366){
                    newYear++;
                    d -= 366;
                } else {break;}
            } else {
                newYear++;
                d -= 365;
            }
        }
        return newYear;
    }
    private static int makeMonth(int d){
        boolean isLeap = (makeYear(d)%4 == 0)?true:false;
        d = daysRemained(d,true);
        int month = 1;
        while(!(d < 29)){
            if(month <= 6){
                if (d>=31){
                    month++;
                    d-=31;}
            } else if(month != 12){
                if (!((d-30)<=0 )){
                    month++;
                    d-=30;
                }
            } else {
                if(isLeap){
                    if(d==30)
                        d-=30;
                    break;
                }else if(d == 29){
                    d-=29;
                }
            }
        }
        return month;
    }
    private static int makeDay(int d){
        if(!isYearLeap(makeYear(d)))
            return daysRemained(d,false) + 2;
        else
            return daysRemained(d,false) + 1;
    }
    /*
     * if type is true it makes whole years as much as possible
     * and returns the remaining days
     * if false it makes whole months as much as possible and
     * return the remaining days
     * */
    private static int daysRemained(int d,boolean type){
        if(type){
            int newYear = jalaliEpochYear;
            while(!(d <= 365)){
                if(newYear%4 == 0){
                    if(d >= 366){
                        newYear++;
                        d -= 366;
                    } else {break;}
                } else {
                    newYear++;
                    d -= 365;
                }
            }
            return d;
        } else {
            boolean isLeap = (makeYear(d)%4 == 0)?true:false;
            d = daysRemained(d,true);
            int month = 1;
            while(!(d < 29)){
                if(month <= 6){
                    if (d>=31){
                        month++;
                        d-=31;}
                } else if(month != 12){
                    if (!((d-30)<=0 )){
                        month++;
                        d-=30;
                    }
                } else {
                    if(isLeap){
                        if(d==30)
                            d-=30;
                        break;
                    }else if(d == 29){
                        d-=29;
                    }
                }
            }
            return d;
        }
    }

    // helpers
    private void construct(PersianDate persianDate){
        year = persianDate.getYear();
        day = persianDate.getDay();
        month = persianDate.getMonth();
        dayOfWeek = persianDate.getDayOfWeek();
        isLeap = isYearLeap(year);
    }

    // returns the day of the week of a specific date
    public static int findDayOfWeek(int iy,int im,int d){
        int y = iy - 1;
        int daysElapsed = 0;
        int leapYears = y / 4;
        int notLeapYears = y - leapYears;
        daysElapsed += (leapYears*366);
        daysElapsed += (notLeapYears * 365);
        int m = im - 1;

        if(m<=6)
            daysElapsed += m*31;
        else{
            daysElapsed += 6*31;
            daysElapsed += (m - 6)*30;
        }
        daysElapsed += d;
        int daysFromEpoch = daysElapsed - (474460);
        int weekDay = daysFromEpoch % 7;

        if(!PersianDate.isYearLeap(iy))
            weekDay--;

        int today = (weekDay + 2) % 7;
        if (today == 0)
            return 7;
        return today;
    }

    public boolean equals(PersianDate rhs) {
        if (this.getDay() == rhs.getDay() &
                this.getYear() == rhs.getYear() &
                this.getMonth() == rhs.getMonth()){
            return true;
        }
        return false;
    }
    public PersianDate(int y, int m,int d){
        this.setDay(d);
        this.setYear(y);
        this.setMonth(m);
    }

    // data members
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private boolean isLeap;
    private Calendar original_date;

    // epoch date
    private final static int epochY = 1921;
    private final static int epochM = 3;
    private final static int epochDay = 21;
    private final static int jalaliEpochYear = 1300;
}