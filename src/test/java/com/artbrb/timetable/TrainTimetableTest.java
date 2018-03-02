package com.artbrb.timetable;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class TrainTimetableTest {

    private static String trainName = "Express train";
    private static String secondTrainName = "Slow train";
    private static Instant nearestDepartureTime = Instant.now().plusSeconds(1000);
    private static Instant departureTime = Instant.now().plusSeconds(10000);
    private static String arrivalStation = "Moscow";
    private static String intermediateStation = "London";

    @Test
    public void testAddNewTrain() throws Exception {
        // given
        TrainTimetable timetable = new TrainTimetable();

        // when
        timetable.addNewTrain(trainName, departureTime, arrivalStation);

        // then
        Train train = timetable.getTrainMap().get(trainName);
        Assert.assertEquals(1, timetable.getTrainMap().size());
        Assert.assertEquals(1, timetable.getStationMap().size());
        Assert.assertEquals(trainName, train.getName());
        Assert.assertEquals(arrivalStation, train.getArrivalStation());
        Assert.assertEquals(departureTime, train.getDepartureTime());
    }

    @Test
    public void testDeleteTrain() throws Exception {
        // given
        TrainTimetable timetable = new TrainTimetable();
        timetable.addNewTrain(trainName, departureTime, arrivalStation);

        // when
        timetable.deleteTrain(trainName);

        // then
        Assert.assertFalse(timetable.getTrainMap().containsKey(trainName));
        Assert.assertEquals(0, timetable.getStationMap().size());
    }

    @Test
    public void testAddIntermediateStation() throws Exception {
        // given
        TrainTimetable timetable = new TrainTimetable();
        timetable.addNewTrain(trainName, departureTime, arrivalStation);

        // when
        timetable.addIntermediateStation(intermediateStation, trainName);

        // then
        Assert.assertEquals(2, timetable.getStationMap().size());
        Assert.assertTrue(timetable.getStationMap().get(intermediateStation).contains(trainName));

    }

    @Test
    public void testDeleteIntermediateStation() throws Exception {
        // given
        TrainTimetable timetable = new TrainTimetable();
        timetable.addNewTrain(trainName, departureTime, arrivalStation);
        timetable.addIntermediateStation(intermediateStation, trainName);

        // when
        timetable.deleteIntermediateStation(trainName, intermediateStation);

        // then
        Assert.assertTrue(timetable.getTrainMap().containsKey(trainName));
        Assert.assertEquals(1, timetable.getStationMap().size());
        Assert.assertTrue(!timetable.getStationMap().containsKey(intermediateStation));
    }

    @Test
    public void testFindNearestTrain() throws Exception {
        // given
        TrainTimetable timetable = new TrainTimetable();
        timetable.addNewTrain(trainName, nearestDepartureTime, arrivalStation);
        timetable.addNewTrain(secondTrainName, departureTime, arrivalStation);

        // when
        String nearestTrain = timetable.findNearestTrain(arrivalStation);

        // then
        Assert.assertEquals(trainName, nearestTrain);
    }
}