package com.kata;

public class Babysitter {
    private static final int PRE_BEDTIME_RATE = 12;
    private static final int POST_BEDTIME_RATE = 8;
    private static final int POST_MIDNIGHT_RATE = 16;
    private static final int EARLIEST_START_HOUR = 17;
    private static final int LATEST_END_HOUR = 28;
    private static final int MIDNIGHT = 24;
    private static final int HOURS_IN_DAY = 24;
    public static final String START_TIME_VALIDATION_ERROR_MESSAGE = "Start time must be after 5:00 PM and before 4:00 AM";
    public static final String MIDDAY_HOUR_VALIDATION_ERROR_MESSAGE = "Time cannot include midday hours";
    public static final String END_TIME_VALIDATION_ERROR_MESSAGE = "End time must be before start time and between 5:00 PM and 4:00 AM";

    public static int getRate(int startTime, int endTime, int bedTime) throws Exception {
        int total = 0;
        int startHour = normalizeHour(startTime);
        int endHour = normalizeHour(endTime);
        int bedHour = normalizeHour(bedTime);
        bedHour = bedHour > MIDNIGHT ? MIDNIGHT : bedHour;

        if (startHour < EARLIEST_START_HOUR || startHour > LATEST_END_HOUR)
            throw new Exception(START_TIME_VALIDATION_ERROR_MESSAGE);

        if (startHour >= LATEST_END_HOUR)
            throw new Exception(MIDDAY_HOUR_VALIDATION_ERROR_MESSAGE);

        if (endHour <= startHour || endHour > LATEST_END_HOUR || endHour < EARLIEST_START_HOUR)
            throw new Exception(END_TIME_VALIDATION_ERROR_MESSAGE);

        for (int hour = startHour; hour < endHour; hour++)
            total += getHourlyRate(hour, bedHour);

        return total;
    }

    private static int normalizeHour(int hour) {
        return hour > LATEST_END_HOUR - HOURS_IN_DAY ? hour : hour + HOURS_IN_DAY;
    }

    private static int getHourlyRate(int hour, int bedHour) {
        if (hour < bedHour)
            return PRE_BEDTIME_RATE;
        if (hour < MIDNIGHT)
            return POST_BEDTIME_RATE;
        return POST_MIDNIGHT_RATE;
    }
}