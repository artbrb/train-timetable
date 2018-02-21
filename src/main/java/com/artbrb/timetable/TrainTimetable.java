package com.artbrb.timetable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TrainTimetable {

    public ArrayList<Train> trains;

    public void addNewTrain(String trainName, String departureTime, String arrivalStation) {
        final String name = trainName;
        Train name = new Train(trainName, departureTime, arrivalStation);
    }

    public void deleteTrain(String trainName) {

    }

    public void addIntermediateSttion(String trainName, String stationName) {

    }

    public void deleteIntermediateStation(String trainName, String stationName) {

    }

    public void findNearestTrain(String stationName, Instant time) {

    }

}
