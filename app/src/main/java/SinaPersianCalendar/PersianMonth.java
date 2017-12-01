package SinaPersianCalendar;

/**
 * Created by sina on 10/14/17.
 */

public class PersianMonth {
    public PersianMonth(){
        year = 1300;
        month = 1;
        generateDays();
    }
    public PersianMonth(int y,int m) {
        year = y;
        month = m;
        generateDays();
    }

    public void printInit() {
        System.out.println(initDate.toString());
    }
    public int getNumberOfDays() {return numberOfDays;}
    public PersianDate[] getDays() {return days;}
    public PersianDate getInitDate() {return initDate;}
    /*
     * *
     * Generate days of the month according to the month number
     * and saves them in the days[] array
     *
     */
    private void generateDays() {

        int limit = 0;
        if(month <= 6)
            limit = 31;
        else if (month != 12)
            limit = 30;
        else if (year % 4 == 0)
            limit = 30;
        else
            limit = 29;

        numberOfDays = limit;
        days = new PersianDate[limit];
        for (int i  = 1;i<=limit;i++) {
            days[i-1] = new PersianDate(year,month,i,
                    PersianDate.findDayOfWeek(year, month, i),PersianDate.isYearLeap(year));

        }
        initDate = days[0];
    }

    private PersianDate days[];			// all days of the month
    private PersianDate initDate;		// date of the first day of the month
    private int numberOfDays;
    private int year;
    private int month;

}
