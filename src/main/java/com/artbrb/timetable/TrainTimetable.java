package com.artbrb.timetable;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainTimetable {
    Map<String, Train> trainHashMap = new HashMap<String, Train>();
    Map<String, ArrayList<String>> stationHashMap = new HashMap<String, ArrayList<String>>();

    public void addNewTrain(String trainName, String departureTime, String arrivalStation) {
        Train newTrain = new Train(trainName, departureTime, arrivalStation);
        trainHashMap.put(trainName, newTrain);
    }

    public void deleteTrain(String stationName, String trainName) {

    }

    public void addIntermediateSttion(String trainName, String stationName) {
        if (stationHashMap.containsKey(stationName)) {
            stationHashMap.get(stationName).add(trainName);
        } else {
            ArrayList<String> list = new ArrayList<String>();
            stationHashMap.put(trainName, list);
        }
    }

    public void deleteIntermediateStation(String trainName, String stationName) {

    }

    public void findNearestTrain(String stationName, Time time) {
    }
}
