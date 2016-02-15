package com.kata;

import junit.framework.*;
import java.util.GregorianCalendar;

/**
 * Created by jwinters on 2/15/16.
 */
public class BabysitterTest extends TestCase {

    public void testStartTimeCannotBeEarlierThanFivePM() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 16, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 2, 4, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 2, 0, 0);
        try {
            Babysitter.getRate(startTime, endTime, bedTime);
            fail("Should throw an exception if start time is before 5:00 PM");
        } catch (Exception e) {
            assert (e instanceof Exception);
            assertEquals("Start time must be after 5:00 PM", e.getMessage());
        }
    }
}