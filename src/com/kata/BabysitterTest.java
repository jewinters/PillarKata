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
            assertEquals("Start time must be after 5:00 PM and before 4:00 AM", e.getMessage());
        }
    }

    public void testEndTimeCannotBeLaterThanFourAM() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 17, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 2, 5, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 2, 0, 0);
        try {
            Babysitter.getRate(startTime, endTime, bedTime);
            fail("Should throw an exception if end time is after 4:00 AM");
        } catch (Exception e){
            assert(e instanceof Exception);
            assertEquals("End time must be before start time and between 5:00 PM and 4:00 AM", e.getMessage());
        }
    }

    public void testEndTimeCannotBeBeforeStartTime() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 17, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 1, 4, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 2, 0, 0);

        try {
            Babysitter.getRate(startTime, endTime, bedTime);
            fail("Should throw an exception if end time is before start time");
        } catch (Exception e){
            assert(e instanceof Exception);
            assertEquals("End time must be before start time and between 5:00 PM and 4:00 AM", e.getMessage());
        }
    }

    public void testStartToBedTimeReturns12PerHour() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 17, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 1, 18, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 1, 18, 0);
        try {
            assertEquals(12, Babysitter.getRate(startTime, endTime, bedTime));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    public void testBedTimeToMidnightReturns8PerHour() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 23, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 1, 24, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 1, 23, 0);
        try {
            assertEquals(8, Babysitter.getRate(startTime, endTime, bedTime));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    public void testMidnightToEndTimeReturns16PerHour() {
        GregorianCalendar startTime = new GregorianCalendar(2016, 0, 1, 0, 0);
        GregorianCalendar endTime = new GregorianCalendar(2016, 0, 1, 1, 0);
        GregorianCalendar bedTime = new GregorianCalendar(2016, 0, 1, 0, 0);
        try {
            assertEquals(16, Babysitter.getRate(startTime, endTime, bedTime));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

}