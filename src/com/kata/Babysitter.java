package com.kata;

import java.util.Calendar;

/**
 * Created by jwinters on 2/15/16.
 */
public class Babysitter {

    private static final int PRE_BEDTIME_RATE = 12;
    private static final int POST_BEDTIME_RATE = 8;
    private static final int POST_MIDNIGHT_RATE = 16;

    private static final int EARLIEST_START_HOUR = 17;
    private static final int LATEST_END_HOUR = 28;
    private static final int MIDNIGHT = 24;
    private static final int HOURS_IN_DAY = 24;

    public static int getRate(Calendar startTime, Calendar endTime, Calendar bedTime) throws Exception {

        int startHour, endHour, bedHour;

        //Turn our Calendars into integer-based hours for ease of use.
        startHour = getHourOfDay(startTime);
        endHour = getHourOfDay(endTime);
        bedHour = getHourOfDay(bedTime);

        int total = 0;

        //Start Time must be after 5:00 PM, but it could also be prior to 4:00 AM.
        if ( startHour < EARLIEST_START_HOUR || startHour > LATEST_END_HOUR )
            throw new Exception("Start time must be after 5:00 PM and before 4:00 AM");

        //End time must be before 4:00 AM
        if ( endTime.before(startTime) || endHour > LATEST_END_HOUR || endHour < EARLIEST_START_HOUR )
            throw new Exception("End time must be before start time and between 5:00 PM and 4:00 AM");


        for (int hour = startHour; hour < endHour; hour++) {
            total += getHourlyRate(hour, bedHour);
        }

        return total;
    }

    private static int getHourOfDay(Calendar time) {
        int hourOfDay = time.get(Calendar.HOUR_OF_DAY);

        if ( time.get(Calendar.HOUR_OF_DAY) <= (LATEST_END_HOUR - HOURS_IN_DAY) )
            hourOfDay += HOURS_IN_DAY;

        return hourOfDay;
    }

    private static int getHourlyRate(int hour, int bedHour) {
        if (hour < bedHour)
            return PRE_BEDTIME_RATE;
        if (hour < MIDNIGHT)
            return POST_BEDTIME_RATE;
        return POST_MIDNIGHT_RATE;

    }
}
