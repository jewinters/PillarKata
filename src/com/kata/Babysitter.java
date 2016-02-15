package com.kata;

import java.util.Calendar;

/**
 * Created by jwinters on 2/15/16.
 */
public class Babysitter {

    public static int getRate(Calendar startTime, Calendar endTime, Calendar bedTime) throws Exception {

        int total = 0;

        //Start Time must be after 5:00 PM, but it could also be prior to 4:00 AM.
        if ( startTime.get(Calendar.HOUR_OF_DAY) < 17 )
            throw new Exception("Start time must be after 5:00 PM");

        if ( endTime.get(Calendar.HOUR_OF_DAY) > 4 )
            throw new Exception("End time must be before 4:00 AM");

        if ( endTime.before(startTime) )
            throw new Exception("End time must be after start time");

        return total;
    }

}
