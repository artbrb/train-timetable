package com.artbrb.timetable;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import javax.print.DocFlavor;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainTimetable {
    private Map<String, Train> trainHashMap = new HashMap<>();
    private Map<String, List<String>> stationHashMap = new HashMap<>();

    public void addNewTrain(String trainName, Instant departureTime, String arrivalStation) {
        Train newTrain = new Train(trainName, departureTime, arrivalStation);
        trainHashMap.put(trainName, newTrain);
        addIntermediateStation(arrivalStation, trainName);
    }

    public void deleteTrain(String trainName) {
        trainHashMap.remove(trainName);
        for (Map.Entry<String, List<String>> entry : stationHashMap.entrySet()) {
            List<String> trains = entry.getValue();
            if (trains.contains(trainName)) {
                trains.remove(trainName);
            }
        }
    }

    public void addIntermediateStation(String stationName, String trainName) {
        if (stationHashMap.containsKey(stationName)) {
            stationHashMap.get(stationName).add(trainName);
        } else {
            List<String> list = new ArrayList<>();
            list.add(trainName);
            stationHashMap.put(stationName, list);
        }
    }

    public void deleteIntermediateStation(String trainName, String stationName) {
        stationHashMap.get(stationName).remove(trainName);
    }

    public String findNearestTrain(String stationName) throws Exception {
        List<String> trains = stationHashMap.get(stationName);
        Instant currentTime = Instant.now();
        Long nearestTrainTime = Long.MAX_VALUE;
        String nearestTrainName = "";
        for (String trainName : trains) {
            Train train = trainHashMap.get(trainName);
            long millisToNextTrain = Duration.between(currentTime, train.getDepartureTime()).toMillis();
            if (millisToNextTrain > 0) {
                if (millisToNextTrain < nearestTrainTime) {
                    nearestTrainName = trainName;
                    nearestTrainTime = millisToNextTrain;
                }
            }
        }

        if (nearestTrainName.isEmpty()) {
            throw new Exception("No train for you");
        }
        return nearestTrainName;
    }
}
