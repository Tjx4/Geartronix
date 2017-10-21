package com.emgr.geartronix.providers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeProvider {

    private SimpleDateFormat formatedDate;
    private int day, hour, minute;
    private final String HOURFORMAT = "H";
    private final String MINUTEFORMAT = "M";
    private final String SECONDFORMAT = "S";

    public DateTimeProvider () {

    }

    public void getCurrentDateAndTime() {
        formatDate("D-M-Y");
        formatedDate.format(new Date());
    }

    public void getCurrentTime() {
        formatDate("H:M:S");
        formatedDate.format(new Date());
    }

    public void getCurrentHour() {
        formatDate(HOURFORMAT);
        hour = Integer.parseInt(formatedDate.format(new Date()));
    }

    public void getCurrentMinute() {
        formatDate(MINUTEFORMAT);
        minute = Integer.parseInt(formatedDate.format(new Date()));
    }

    private void formatDate(String format) {
        formatedDate = new SimpleDateFormat(format);
    }

}
