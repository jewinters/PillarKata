package com.kata;

import java.util.Calendar;

public class Babysitter {

    private static final int PRE_BEDTIME_RATE = 12;
    private static final int POST_BEDTIME_RATE = 8;
    private static final int POST_MIDNIGHT_RATE = 16;

    private static final int EARLIEST_START_HOUR = 17;
    private static final int LATEST_END_HOUR = 28;
    private static final int MIDNIGHT = 24;
    private static final int HOURS_IN_DAY = 24;

    private static final boolean ROUND_UP = true;

    public static int getRate(Calendar startTime, Calendar endTime, Calendar bedTime) throws Exception {
        int startHour = getHourOfDay(startTime);
        int endHour = getHourOfDay(endTime, ROUND_UP);
        int bedHour = getHourOfDay(bedTime, ROUND_UP);
        return getRate(startHour, endHour, bedHour);
    }

    public static int getRate(int startHour, int endHour, int bedHour) throws Exception {
        int total = 0;

        if ( startHour < EARLIEST_START_HOUR || startHour > LATEST_END_HOUR )
            throw new Exception("Start time must be after 5:00 PM and before 4:00 AM");

        if ( startHour >= LATEST_END_HOUR )
            throw new Exception("Time cannot include midday hours");

        if ( endHour <= startHour || endHour > LATEST_END_HOUR || endHour < EARLIEST_START_HOUR ) {
            throw new Exception("End time must be before start time and between 5:00 PM and 4:00 AM");
        }

        if ( bedHour > MIDNIGHT )
            bedHour = MIDNIGHT;

        for (int hour = startHour; hour < endHour; hour++)
            total += getHourlyRate(hour, bedHour);

        return total;
    }

    private static int getHourOfDay(Calendar time) {
        return getHourOfDay(time, false);
    }

    private static int getHourOfDay(Calendar time, Boolean roundUp) {
        int hourOfDay = time.get(Calendar.HOUR_OF_DAY);

        if ( time.get(Calendar.HOUR_OF_DAY) <= (LATEST_END_HOUR - HOURS_IN_DAY) )
            hourOfDay += HOURS_IN_DAY;

        if ( roundUp && time.get(Calendar.MINUTE) > 0 )
            hourOfDay++;

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
