package com.kata;

import junit.framework.*;

public class BabysitterTest extends TestCase {

    public void testStartTimeCannotBeEarlierThanFivePM() {
        try {
            Babysitter.getRate(16, 4, 24);
            fail("Should throw an exception if start time is before 5:00 PM");
        } catch (Exception e) {
            assertEquals(Babysitter.START_TIME_VALIDATION_ERROR_MESSAGE, e.getMessage());
        }
    }

    public void testEndTimeCannotBeLaterThanFourAM() {
        try {
            Babysitter.getRate(17, 5, 24);
            fail("Should throw an exception if end time is after 4:00 AM");
        } catch (Exception e){
            assertEquals(Babysitter.END_TIME_VALIDATION_ERROR_MESSAGE, e.getMessage());
        }
    }

    public void testEndTimeCannotBeBeforeStartTime() {
        try {
            Babysitter.getRate(17, 16, 24);
            fail("Should throw an exception if end time is before start time");
        } catch (Exception e){
            assertEquals(Babysitter.END_TIME_VALIDATION_ERROR_MESSAGE, e.getMessage());
        }
    }

    public void testTimeIncludesDaytimeHoursThrowsEndTimeException() {
        try {
            Babysitter.getRate(4, 18, 24);
            fail("Should throw an exception if time includes hours between 4:00 AM and 5:00 PM");
        } catch (Exception e) {
            assertEquals(Babysitter.MIDDAY_HOUR_VALIDATION_ERROR_MESSAGE, e.getMessage());
        }
    }

    public void testStartToBedTimeReturns12PerHour() throws Exception {
        assertEquals(12, Babysitter.getRate(17, 18, 24));
    }

    public void testBedTimeToMidnightReturns8PerHour() throws Exception {
        assertEquals(8, Babysitter.getRate(23, 24, 23));
    }

    public void testMidnightToEndTimeReturns16PerHour() throws Exception {
        assertEquals(16, Babysitter.getRate(24, 1, 24));
    }

    public void testBedTimeAfterMidnightReturns16PerHour() throws Exception {
        assertEquals(16, Babysitter.getRate(24, 1, 1));
    }
}