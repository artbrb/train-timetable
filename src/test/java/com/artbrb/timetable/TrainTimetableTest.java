package com.artbrb.timetable;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class TrainTimetableTest {

    @Test
    public void test() {
        TrainTimetable trainTimetable = new TrainTimetable();
        trainTimetable.addNewTrain("fast", Instant.now(),"America");
        Assert.assertEquals("Artem", "Art" + "em");
        Assert.assertNotNull(trainTimetable);
    }
}
