package co.za.geartronix.providers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeProvider {

    private SimpleDateFormat formatedDate;
    private int dateTime, time, day, hour, minute;
    private final String DAYFORMAT = "d";
    private final String HOURFORMAT = "H";
    private final String MINUTEFORMAT = "m";
    private final String TIMEFORMAT = "H:m:s";
    private final String DATETIMEFORMAT = "d-m-y "+TIMEFORMAT;

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
//Todo: fix this
        String dateFormated = formatedDate.format(new Date());
      //  timeUnit = Integer.parseInt(dateFormated);
    }

    public String formatAndSetTime(String format, int timeUnit) {
        formatDate(format);
        setTimeUnit(timeUnit);

        return formatedDate.toString();
    }

    private void formatDate(String format) {
        formatedDate = new SimpleDateFormat(format);
    }

    public String getFormatedDateAndTime() {
        String dateTime = getCurrentDateAndTime();
        String[] fmtDt = dateTime.split(" ");
        String combined = fmtDt[0]+fmtDt[1];
        String[] fmtDt2 = combined.split("-");

        String formatedDate = "";
        for(String cd : fmtDt2) {
            formatedDate += cd;
        }

        return formatedDate;
    }

}
