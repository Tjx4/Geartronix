package co.za.geartronix.providers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeProvider {

    private SimpleDateFormat formatedDate;
    private int dateTime, time, day, hour, minute;
    private final String DAYFORMAT = "D";
    private final String HOURFORMAT = "H";
    private final String MINUTEFORMAT = "M";
    private final String TIMEFORMAT = "H:M:S";
    private final String DATETIMEFORMAT = "D-M-Y H:M:S";

    public DateTimeProvider () {

    }

    public String getCurrentDateAndTime() {
        return formatAndSetTime(DATETIMEFORMAT, dateTime);
    }

    public String getCurrentTime() {
        return formatAndSetTime(TIMEFORMAT, time);
    }

    public String getCurrentDay() {
        return formatAndSetTime(DAYFORMAT, day);
    }

    public String getCurrentHour() {
        return formatAndSetTime(HOURFORMAT, hour);
    }

    public String getCurrentMinute() {
        return formatAndSetTime(MINUTEFORMAT, minute);
    }

    public void setTimeUnit(int timeUnit) {
        timeUnit = Integer.parseInt(formatedDate.format(new Date()));
    }

    public String formatAndSetTime(String format, int timeUnit) {
        formatDate(format);
        setTimeUnit(timeUnit);

        return formatedDate.toString();
    }

    private void formatDate(String format) {
        formatedDate = new SimpleDateFormat(format);
    }

}
